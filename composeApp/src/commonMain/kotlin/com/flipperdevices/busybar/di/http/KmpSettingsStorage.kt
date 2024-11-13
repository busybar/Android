@file:OptIn(ExperimentalSerializationApi::class)

package com.flipperdevices.busybar.di.http

import com.flipperdevices.busybar.settings.model.SettingsEnum
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.Settings
import com.russhwolf.settings.serialization.decodeValue
import com.russhwolf.settings.serialization.decodeValueOrNull
import com.russhwolf.settings.serialization.encodeValue
import com.russhwolf.settings.set
import io.ktor.client.plugins.cookies.CookiesStorage
import io.ktor.client.plugins.cookies.fillDefaults
import io.ktor.client.plugins.cookies.matches
import io.ktor.http.Cookie
import io.ktor.http.Url
import io.ktor.util.date.getTimeMillis
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import me.tatarka.inject.annotations.Inject
import kotlin.math.min

@Serializable
private data class CookieWithTimestamp(
    @SerialName("cookie")
    val cookie: Cookie,
    @SerialName("created_at")
    val createdAt: Long
)

@Serializable
private data class CookieData(
    @SerialName("cookies")
    val cookies: List<CookieWithTimestamp> = emptyList()
)

@ExperimentalSettingsApi
@Inject
class KmpSettingsStorage(
    private val settings: Settings
) : CookiesStorage {
    private var cookieData =
        settings.decodeValue<CookieData>(SettingsEnum.SESSIONS.key, CookieData())

    private var oldestCookie = 0L
    private val mutex = Mutex()

    override suspend fun get(requestUrl: Url): List<Cookie> = mutex.withLock {
        val now = clock()
        if (now >= oldestCookie) cleanup(now)

        val cookies = cookieData.cookies.filter { it.cookie.matches(requestUrl) }.map { it.cookie }
        return@withLock cookies
    }

    override suspend fun addCookie(requestUrl: Url, cookie: Cookie) {
        with(cookie) {
            if (name.isBlank()) return
        }

        mutex.withLock {
            val newList = cookieData.cookies.toMutableList()
            newList.removeAll { (existingCookie, _) ->
                existingCookie.name == cookie.name && existingCookie.matches(requestUrl)
            }
            val createdAt = clock()
            newList.add(
                CookieWithTimestamp(
                    cookie.fillDefaults(requestUrl),
                    createdAt
                )
            )

            cookie.maxAgeOrExpires(createdAt)?.let {
                if (oldestCookie > it) {
                    oldestCookie = it
                }
            }
            cookieData = CookieData(newList)
            settings.encodeValue(SettingsEnum.SESSIONS.key, cookieData)
        }
    }

    override fun close() {
    }

    private fun cleanup(timestamp: Long) {
        val newList = cookieData.cookies.toMutableList()

        newList.removeAll { (cookie, createdAt) ->
            val expires = cookie.maxAgeOrExpires(createdAt) ?: return@removeAll false
            expires < timestamp
        }

        val newOldest = newList.fold(Long.MAX_VALUE) { acc, (cookie, createdAt) ->
            cookie.maxAgeOrExpires(createdAt)?.let { min(acc, it) } ?: acc
        }

        oldestCookie = newOldest

        cookieData = CookieData(newList)
        settings.encodeValue(SettingsEnum.SESSIONS.key, cookieData)
    }

    private fun Cookie.maxAgeOrExpires(createdAt: Long): Long? =
        maxAge?.let { createdAt + it * 1000L } ?: expires?.timestamp
}

private fun clock() = getTimeMillis()