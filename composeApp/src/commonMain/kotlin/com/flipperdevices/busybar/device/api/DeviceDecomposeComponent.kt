@file:OptIn(ExperimentalSettingsApi::class)

package com.flipperdevices.busybar.device.api

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.busybar.apps.composable.apps.BusyBarApp
import com.flipperdevices.busybar.core.decompose.DecomposeComponent
import com.flipperdevices.busybar.device.composable.DeviceScreenComposable
import com.flipperdevices.busybar.root.api.RootNavigationApi
import com.flipperdevices.busybar.root.config.RootScreenConfig
import com.flipperdevices.busybar.settings.model.SettingsEnum
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.FlowSettings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class DeviceDecomposeComponent(
    @Assisted componentContext: ComponentContext,
    @Assisted private val navigationApi: RootNavigationApi,
    private val settings: FlowSettings
) : DecomposeComponent, ComponentContext by componentContext {
    private val currentAppFlow = MutableStateFlow(BusyBarApp.default)

    @Composable
    override fun Render(modifier: Modifier) {
        val currentApp by currentAppFlow.collectAsState()
        val devMode by remember {
            settings.getBooleanFlow(SettingsEnum.DEV_MODE.key, false)
        }.collectAsState(false)
        DeviceScreenComposable(
            modifier.statusBarsPadding(),
            busyBarApp = currentApp,
            onChangeApp = { navigationApi.push(RootScreenConfig.APPS) },
            onClick = {},
            onOpenSettings = { navigationApi.push(RootScreenConfig.SETTINGS) },
            devMode = devMode
        )
    }

    fun onAppSelected(barApp: BusyBarApp) {
        currentAppFlow.update { barApp }
    }
}