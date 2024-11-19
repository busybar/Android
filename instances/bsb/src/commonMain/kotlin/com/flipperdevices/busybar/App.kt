package com.flipperdevices.busybar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.flipperdevices.bsb.core.theme.BusyBarTheme
import com.flipperdevices.bsb.preference.model.SettingsEnum
import com.flipperdevices.bsb.root.api.RootDecomposeComponent
import com.flipperdevices.busybar.di.AppComponent

@Composable
fun App(
    rootComponent: RootDecomposeComponent,
    appComponent: AppComponent
) {
    val isDarkMode by remember {
        appComponent.preferenceApi.getFlow(
            SettingsEnum.DARK_THEME,
            false
        )
    }.collectAsState(false)

    BusyBarTheme(isDarkMode) {
        rootComponent.Render()
    }
}