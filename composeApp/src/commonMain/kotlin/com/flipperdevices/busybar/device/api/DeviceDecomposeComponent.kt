package com.flipperdevices.busybar.device.api

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.busybar.core.decompose.DecomposeComponent
import com.flipperdevices.busybar.device.composable.DeviceScreenComposable
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class DeviceDecomposeComponent(
    @Assisted componentContext: ComponentContext
) : DecomposeComponent, ComponentContext by componentContext {
    @Composable
    override fun Render(modifier: Modifier) {
        DeviceScreenComposable(
            modifier.statusBarsPadding(),
            onClick = {}
        )
    }
}