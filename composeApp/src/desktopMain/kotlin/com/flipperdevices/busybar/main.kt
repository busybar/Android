package com.flipperdevices.busybar

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.flipperdevices.busybar.di.createAppComponent

fun main() {
    val lifecycle = LifecycleRegistry()
    // Always create the root component outside Compose on the UI thread
    val appComponent = createAppComponent()
    val root = runOnUiThread {
        appComponent.rootComponent(
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
            App(root)
        }
    }
}