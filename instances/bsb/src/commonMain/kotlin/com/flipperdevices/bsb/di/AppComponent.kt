package com.flipperdevices.bsb.di

import com.flipperdevices.bsb.preference.api.PreferenceApi
import com.flipperdevices.bsb.root.api.RootDecomposeComponent
import com.russhwolf.settings.ObservableSettings
import kotlinx.coroutines.CoroutineScope
import me.tatarka.inject.annotations.Provides

interface AppComponent {
    @get:Provides val observableSettings: ObservableSettings

    @get:Provides val scope: CoroutineScope

    val rootDecomposeComponentFactory: RootDecomposeComponent.Factory
    val preferenceApi: PreferenceApi
}
