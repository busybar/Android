package com.flipperdevices.bsb

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.flipperdevices.bsb.core.theme.BusyBarTheme
import com.flipperdevices.bsb.core.theme.LocalPallet
import com.flipperdevices.bsb.di.AppComponent
import com.flipperdevices.bsb.preference.model.SettingsEnum
import com.flipperdevices.bsb.root.api.RootDecomposeComponent

@Composable
fun App(
    rootComponent: RootDecomposeComponent,
    appComponent: AppComponent,
    modifier: Modifier = Modifier
) {
    val isDarkMode by remember {
        appComponent.preferenceApi.getFlowBoolean(
            SettingsEnum.DARK_THEME,
            false
        )
    }.collectAsState(false)

    BusyBarTheme(isDarkMode) {
        rootComponent.Render(
            modifier
                .fillMaxSize()
                .background(LocalPallet.current.black.onColor)
        )
    }
}
