package com.flipperdevices.bsb.cloud.di.http

import com.flipperdevices.bsb.preference.api.PreferenceApi
import com.flipperdevices.bsb.preference.api.get
import com.flipperdevices.bsb.preference.api.set
import com.flipperdevices.bsb.preference.model.SettingsEnum
import com.flipperdevices.core.di.AppGraph
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
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding
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

@Inject
@ContributesBinding(AppGraph::class, CookiesStorage::class)
class KmpSettingsStorage(
    private val preference: PreferenceApi
) : CookiesStorage {
    private var cookieData = preference.get(SettingsEnum.SESSIONS, CookieData())

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
            preference.set(SettingsEnum.SESSIONS, cookieData)
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
        preference.set(SettingsEnum.SESSIONS, cookieData)
    }

    private fun Cookie.maxAgeOrExpires(createdAt: Long): Long? =
        maxAge?.let { createdAt + it * 1000L } ?: expires?.timestamp
}

private fun clock() = getTimeMillis()