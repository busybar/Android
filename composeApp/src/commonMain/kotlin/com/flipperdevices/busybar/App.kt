package com.flipperdevices.busybar

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import com.flipperdevices.busybar.core.theme.BusyBarTheme
import com.flipperdevices.busybar.root.api.RootDecomposeComponent

@Composable
@Preview
fun App(rootComponent: RootDecomposeComponent) {
    val darkMode by remember { rootComponent.isDarkModeFlow() }.collectAsState(false)
    BusyBarTheme(darkMode = darkMode) {
        Surface {
            rootComponent.Render(Modifier.fillMaxSize())
        }
    }
}