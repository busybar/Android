package com.flipperdevices.bsb

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.flipperdevices.bsb.di.DesktopAppComponent
import com.flipperdevices.bsb.di.createAppComponent
import com.russhwolf.settings.PreferencesSettings
import java.util.prefs.Preferences

fun main() {
    val preferences = Preferences.userRoot()
    val settings = PreferencesSettings(preferences)

    val lifecycle = LifecycleRegistry()
    // Always create the root component outside Compose on the UI thread
    val appComponent = DesktopAppComponent::class.create(settings)
    val root = runOnUiThread {
        appComponent.rootDecomposeComponentFactory(
            DefaultComponentContext(lifecycle = lifecycle),
        )
    }
    application {
        val windowState = rememberWindowState()

        LifecycleController(lifecycle, windowState)
        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            title = "BusyStatusBar",
        ) {
            App(root, appComponent)
        }
    }
}