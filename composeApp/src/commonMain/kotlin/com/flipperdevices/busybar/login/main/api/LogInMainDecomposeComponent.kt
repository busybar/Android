package com.flipperdevices.busybar.login.main.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.busybar.core.decompose.DecomposeComponent
import com.flipperdevices.busybar.login.main.composable.LogInMainComposable
import com.flipperdevices.busybar.login.main.composable.LogInMainComposableScreen
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class LogInMainDecomposeComponent(
    @Assisted componentContext: ComponentContext,
) : DecomposeComponent, ComponentContext by componentContext  {
    @Composable
    override fun Render(modifier: Modifier) {
        LogInMainComposableScreen()
    }
}