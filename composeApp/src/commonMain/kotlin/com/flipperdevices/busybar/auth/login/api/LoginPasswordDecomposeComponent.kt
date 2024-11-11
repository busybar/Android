package com.flipperdevices.busybar.auth.login.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.busybar.auth.login.composable.LoginPasswordComposableScreen
import com.flipperdevices.busybar.auth.login.composable.LoginPasswordScreenComposable
import com.flipperdevices.busybar.core.decompose.DecomposeComponent
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class LoginPasswordDecomposeComponent(
    @Assisted componentContext: ComponentContext
) : DecomposeComponent, ComponentContext by componentContext {
    @Composable
    override fun Render(modifier: Modifier) {
        LoginPasswordScreenComposable()
    }
}