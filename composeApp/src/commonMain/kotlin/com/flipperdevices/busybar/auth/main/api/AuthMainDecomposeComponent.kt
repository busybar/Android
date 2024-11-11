package com.flipperdevices.busybar.auth.main.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.busybar.core.decompose.DecomposeComponent
import com.flipperdevices.busybar.auth.main.composable.AuthMainComposableScreen
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class AuthMainDecomposeComponent(
    @Assisted componentContext: ComponentContext,
) : DecomposeComponent, ComponentContext by componentContext  {
    @Composable
    override fun Render(modifier: Modifier) {
        AuthMainComposableScreen()
    }
}