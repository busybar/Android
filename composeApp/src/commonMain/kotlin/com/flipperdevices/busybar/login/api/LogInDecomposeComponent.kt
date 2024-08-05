package com.flipperdevices.busybar.login.api

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.busybar.core.decompose.ScreenDecomposeComponent
import com.flipperdevices.busybar.login.composable.LogInScreenComposable
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class LogInDecomposeComponent(
    @Assisted componentContext: ComponentContext
) : ScreenDecomposeComponent(componentContext) {
    @Composable
    override fun Render(modifier: Modifier) {
        LogInScreenComposable(
            modifier.statusBarsPadding()
        )
    }
}