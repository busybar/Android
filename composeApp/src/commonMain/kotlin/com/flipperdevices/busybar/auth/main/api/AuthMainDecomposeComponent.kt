package com.flipperdevices.busybar.auth.main.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.flipperdevices.busybar.auth.common.model.AuthRootNavigationConfig
import com.flipperdevices.busybar.core.decompose.DecomposeComponent
import com.flipperdevices.busybar.auth.main.composable.AuthMainComposableScreen
import com.flipperdevices.busybar.auth.main.viewmodel.AuthMainViewModel
import com.flipperdevices.busybar.core.decompose.viewModelWithFactoryWithoutRemember
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class AuthMainDecomposeComponent(
    @Assisted componentContext: ComponentContext,
    @Assisted authNavigation: StackNavigation<AuthRootNavigationConfig>,
    authMainViewModel: (StackNavigation<AuthRootNavigationConfig>) -> AuthMainViewModel
) : DecomposeComponent, ComponentContext by componentContext {
    val authViewModel = viewModelWithFactoryWithoutRemember(authNavigation) {
        authMainViewModel(authNavigation)
    }

    @Composable
    override fun Render(modifier: Modifier) {
        val state by authViewModel.getState().collectAsState()
        AuthMainComposableScreen(
            state,
            authViewModel::onLogin
        )
    }
}