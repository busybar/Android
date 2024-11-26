package com.flipperdevices.bsb.di

import com.flipperdevices.bsb.preference.api.PreferenceApi
import com.flipperdevices.bsb.root.api.RootDecomposeComponent

interface AppComponent {
    val rootDecomposeComponentFactory: RootDecomposeComponent.Factory
    val preferenceApi: PreferenceApi
}