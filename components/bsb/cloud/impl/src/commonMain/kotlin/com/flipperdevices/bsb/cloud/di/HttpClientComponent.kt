package com.flipperdevices.bsb.cloud.di

import com.flipperdevices.core.di.AppGraph
import com.flipperdevices.core.log.TaggedLogger
import com.flipperdevices.core.log.verbose
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.cookies.CookiesStorage
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.ContributesTo

private val ktorTimber = TaggedLogger("Ktor")

@ContributesTo(AppGraph::class)
interface HttpClientComponent {
    @Provides
    fun provideHttpClient(
        cookiesStorage: CookiesStorage
    ): HttpClient = getHttpClient(cookiesStorage)
}

fun getHttpClient(cookiesStorage: CookiesStorage) = HttpClient(httpEngine()) {
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                coerceInputValues = true
            }
        )
    }

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                ktorTimber.verbose { message }
            }
        }
        level = LogLevel.INFO
    }

    install(HttpCookies) {
        storage = cookiesStorage
    }

    install(DefaultRequest) {
        header(HttpHeaders.ContentType, ContentType.Application.Json)
    }
}

expect fun httpEngine(): HttpClientEngine