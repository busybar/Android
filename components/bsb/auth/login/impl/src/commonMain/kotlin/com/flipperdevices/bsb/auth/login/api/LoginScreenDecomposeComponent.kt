package com.flipperdevices.bsb.auth.login.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.bsb.auth.login.composable.LoginPasswordScreenComposable
import com.flipperdevices.bsb.auth.login.viewmodel.SignInViewModel
import com.flipperdevices.core.ui.lifecycle.viewModelWithFactoryWithoutRemember
import com.flipperdevices.ui.decompose.DecomposeOnBackParameter
import com.flipperdevices.ui.decompose.ScreenDecomposeComponent
import kotlinx.datetime.Instant
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class LoginScreenDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
    @Assisted private val onBack: DecomposeOnBackParameter,
    @Assisted private val email: String,
    @Assisted private val onComplete: () -> Unit,
    @Assisted onForgetPassword: (email: String, codeExpiryTime: Instant) -> Unit,
    signInViewModel: (
        email: String,
        onComplete: () -> Unit,
        onForgetPassword: (email: String, codeExpiryTime: Instant) -> Unit
    ) -> SignInViewModel
) : ScreenDecomposeComponent(componentContext) {
    private val signInViewModel = viewModelWithFactoryWithoutRemember(email) {
        signInViewModel(email, onComplete, onForgetPassword)
    }

    @Composable
    override fun Render(modifier: Modifier) {
        val state by signInViewModel.getState().collectAsState()

        LoginPasswordScreenComposable(
            state = state,
            email = email,
            onBack = onBack::invoke,
            onLogin = signInViewModel::onLogin,
            onForgotPassword = signInViewModel::onForgotPassword
        )
    }
}