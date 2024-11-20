package com.flipperdevices.bsb.auth.main.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.flipperdevices.bsb.auth.main.composable.AuthMainComposableScreen
import com.flipperdevices.bsb.auth.main.model.AuthRootNavigationConfig
import com.flipperdevices.bsb.auth.main.viewmodel.AuthMainViewModel
import com.flipperdevices.core.ui.lifecycle.viewModelWithFactoryWithoutRemember
import com.flipperdevices.ui.decompose.ScreenDecomposeComponent
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class MainScreenDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
    @Assisted authNavigation: StackNavigation<AuthRootNavigationConfig>,
    authMainViewModel: (StackNavigation<AuthRootNavigationConfig>) -> AuthMainViewModel
) : ScreenDecomposeComponent(componentContext) {
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