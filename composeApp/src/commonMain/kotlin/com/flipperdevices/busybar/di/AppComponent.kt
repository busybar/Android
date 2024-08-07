package com.flipperdevices.busybar.di

import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.busybar.root.api.RootDecomposeComponent
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.KmpComponentCreate

@Component
abstract class AppComponent {
    abstract val rootComponent: (componentContext: ComponentContext) -> RootDecomposeComponent
}

@KmpComponentCreate
expect fun createAppComponent(): AppComponent
