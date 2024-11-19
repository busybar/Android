package com.flipperdevices.busybar

import androidx.compose.runtime.Composable
import com.flipperdevices.bsb.root.api.RootDecomposeComponent

@Composable
fun App(rootComponent: RootDecomposeComponent) {
    rootComponent.Render()
}