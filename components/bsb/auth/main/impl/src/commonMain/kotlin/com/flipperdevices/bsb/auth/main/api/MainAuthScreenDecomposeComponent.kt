package com.flipperdevices.bsb.auth.main.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.flipperdevices.bsb.auth.main.composable.AuthMainComposableScreen
import com.flipperdevices.bsb.auth.main.model.AuthMainState
import com.flipperdevices.bsb.auth.main.model.AuthRootNavigationConfig
import com.flipperdevices.bsb.auth.main.viewmodel.AuthMainViewModel
import com.flipperdevices.bsb.auth.within.main.api.SignWithInMainDecomposeComponent
import com.flipperdevices.bsb.auth.within.main.model.SignWithInState
import com.flipperdevices.core.ui.lifecycle.viewModelWithFactoryWithoutRemember
import com.flipperdevices.ui.decompose.ScreenDecomposeComponent
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class MainScreenDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
    @Assisted authNavigation: StackNavigation<AuthRootNavigationConfig>,
    @Assisted onComplete: () -> Unit,
    authMainViewModel: (
        StackNavigation<AuthRootNavigationConfig>,
        onComplete: () -> Unit
    ) -> AuthMainViewModel,
    signWithInMainDecomposeComponent: SignWithInMainDecomposeComponent.Factory
) : ScreenDecomposeComponent(componentContext) {
    private val authViewModel = viewModelWithFactoryWithoutRemember(authNavigation to onComplete) {
        authMainViewModel(authNavigation, onComplete)
    }
    private val signWithInDecomposeComponent = signWithInMainDecomposeComponent(
        componentContext = childContext("signWithIn_main"),
        withInStateListener = authViewModel
    )

    @Composable
    override fun Render(modifier: Modifier) {
        val state by authViewModel.getState().collectAsState()
        AuthMainComposableScreen(
            state,
            authViewModel::onLogin,
            signInWith = { signInWithModifier ->
                signWithInDecomposeComponent.Render(
                    signInWithModifier,
                    state.toSignWithInState()
                )
            }
        )
    }
}

fun AuthMainState.toSignWithInState() = when (this) {
    AuthMainState.AuthInProgress -> SignWithInState.IN_PROGRESS
    AuthMainState.WaitingForInput -> SignWithInState.WAITING_INPUT
}