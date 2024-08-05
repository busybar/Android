package com.flipperdevices.busybar.di

import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.busybar.bottombar.api.BottomBarDecomposeComponentImpl
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.KmpComponentCreate

typealias RootDecomposeComponent = BottomBarDecomposeComponentImpl

@Component
abstract class AppComponent {
    abstract val rootComponent: (componentContext: ComponentContext) -> RootDecomposeComponent
}

@KmpComponentCreate
expect fun createAppComponent(): AppComponent
