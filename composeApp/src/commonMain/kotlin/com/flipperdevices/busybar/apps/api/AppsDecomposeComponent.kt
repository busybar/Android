package com.flipperdevices.busybar.apps.api

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.busybar.apps.composable.AppsScreenComposable
import com.flipperdevices.busybar.core.decompose.ScreenDecomposeComponent
import com.flipperdevices.busybar.device.composable.DeviceScreenComposable
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class AppsDecomposeComponent(
    @Assisted componentContext: ComponentContext
) : ScreenDecomposeComponent(componentContext) {
    @Composable
    override fun Render(modifier: Modifier) {
        AppsScreenComposable(
            modifier.statusBarsPadding(),
            onClick = {}
        )
    }
}