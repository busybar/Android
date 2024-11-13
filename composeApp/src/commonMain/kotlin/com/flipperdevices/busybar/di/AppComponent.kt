@file:OptIn(ExperimentalSettingsApi::class)

package com.flipperdevices.busybar.di

import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.busybar.di.http.KmpSettingsStorage
import com.flipperdevices.busybar.root.api.RootDecomposeComponent
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.toFlowSettings
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.KmpComponentCreate
import me.tatarka.inject.annotations.Provides

@Component
abstract class AppComponent(
    @get:Provides protected val observableSettings: ObservableSettings
) {
    abstract val rootComponent: (componentContext: ComponentContext) -> RootDecomposeComponent

    @OptIn(ExperimentalSettingsApi::class)
    @get:Provides
    val flowSettings = observableSettings.toFlowSettings()

    @Provides
    fun provideSimpleSettings(): Settings = observableSettings

    @Provides
    fun provideHttpClient(
        settingsStorage: KmpSettingsStorage
    ) = createHttpClient(settingsStorage)
}

@KmpComponentCreate
expect fun createAppComponent(
    settings: ObservableSettings
): AppComponent