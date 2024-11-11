package com.flipperdevices.busybar.di

import io.ktor.client.HttpClient
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component
abstract class HttpClientComponent {
    @Provides
    fun provideHttpClient() = httpClient()
}

expect fun httpClient(): HttpClient