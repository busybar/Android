package com.flipperdevices.busybar.device.api

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.busybar.apps.composable.apps.BusyBarApp
import com.flipperdevices.busybar.core.decompose.DecomposeComponent
import com.flipperdevices.busybar.device.composable.DeviceScreenComposable
import com.flipperdevices.busybar.root.api.RootNavigationApi
import com.flipperdevices.busybar.root.config.RootScreenConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class DeviceDecomposeComponent(
    @Assisted componentContext: ComponentContext,
    @Assisted private val navigationApi: RootNavigationApi
) : DecomposeComponent, ComponentContext by componentContext {
    private val currentAppFlow = MutableStateFlow(BusyBarApp.default)

    @Composable
    override fun Render(modifier: Modifier) {
        val currentApp by currentAppFlow.collectAsState()
        DeviceScreenComposable(
            modifier.statusBarsPadding(),
            busyBarApp = currentApp,
            onChangeApp = { navigationApi.push(RootScreenConfig.APPS) },
            onClick = {}
        )
    }

    fun onAppSelected(barApp: BusyBarApp) {
        currentAppFlow.update { barApp }
    }
}