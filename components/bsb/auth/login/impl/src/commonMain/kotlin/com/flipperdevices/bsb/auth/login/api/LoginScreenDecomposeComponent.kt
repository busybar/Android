package com.flipperdevices.bsb.auth.login.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.bsb.auth.login.composable.LoginPasswordScreenComposable
import com.flipperdevices.bsb.auth.login.viewmodel.SignInViewModel
import com.flipperdevices.core.ui.lifecycle.viewModelWithFactory
import com.flipperdevices.ui.decompose.DecomposeOnBackParameter
import com.flipperdevices.ui.decompose.ScreenDecomposeComponent
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class LoginScreenDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
    @Assisted private val onBack: DecomposeOnBackParameter,
    @Assisted private val email: String,
    @Assisted private val onComplete: () -> Unit,
    @Assisted private val onForgetPassword: () -> Unit,
    @Assisted private val preFilledPassword: String?,
    signInViewModel: (
        email: String,
        onComplete: () -> Unit
    ) -> SignInViewModel
) : ScreenDecomposeComponent(componentContext) {
    private val signInViewModel = viewModelWithFactory(email) {
        signInViewModel(email, onComplete)
    }

    @Composable
    override fun Render(modifier: Modifier) {
        val state by signInViewModel.getState().collectAsState()

        LoginPasswordScreenComposable(
            modifier = modifier,
            state = state,
            email = email,
            onBack = onBack::invoke,
            onLogin = signInViewModel::onLogin,
            onForgotPassword = onForgetPassword,
            preFilledPassword = preFilledPassword
        )
    }
}