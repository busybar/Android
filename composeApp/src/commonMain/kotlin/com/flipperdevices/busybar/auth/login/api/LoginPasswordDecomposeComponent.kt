package com.flipperdevices.busybar.auth.login.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.busybar.auth.login.composable.LoginPasswordScreenComposable
import com.flipperdevices.busybar.auth.login.viewmodel.SignInViewModel
import com.flipperdevices.busybar.core.decompose.DecomposeComponent
import com.flipperdevices.busybar.core.decompose.DecomposeOnBackParameter
import com.flipperdevices.busybar.core.decompose.viewModelWithFactoryWithoutRemember
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class LoginPasswordDecomposeComponent(
    @Assisted componentContext: ComponentContext,
    @Assisted private val onBack: DecomposeOnBackParameter,
    @Assisted private val email: String,
    signInViewModel: (
        email: String,
        onComplete: () -> Unit
    ) -> SignInViewModel
) : DecomposeComponent, ComponentContext by componentContext {
    private val signInViewModel = viewModelWithFactoryWithoutRemember(email) {
        signInViewModel(email, {})
    }

    @Composable
    override fun Render(modifier: Modifier) {
        val state by signInViewModel.getState().collectAsState()

        LoginPasswordScreenComposable(
            state = state,
            email = email,
            onBack = onBack::invoke,
            onLogin = signInViewModel::onLogin
        )
    }
}