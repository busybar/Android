package com.flipperdevices.bsb.auth.otp.screen.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.bsb.auth.otp.screen.composable.AuthOtpScreenComposable
import com.flipperdevices.bsb.auth.otp.screen.model.AuthOtpType
import com.flipperdevices.bsb.auth.otp.screen.model.InternalAuthOtpType
import com.flipperdevices.bsb.auth.otp.screen.model.toInternalAuthOtpType
import com.flipperdevices.bsb.auth.otp.screen.viewmodel.AuthOtpScreenViewModel
import com.flipperdevices.core.di.AppGraph
import com.flipperdevices.core.ui.lifecycle.viewModelWithFactoryWithoutRemember
import com.flipperdevices.ui.decompose.DecomposeOnBackParameter
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@Inject
class AuthOtpScreenDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
    @Assisted private val onBack: DecomposeOnBackParameter,
    @Assisted otpType: AuthOtpType,
    private val viewModelFactory: (InternalAuthOtpType) -> AuthOtpScreenViewModel
) : AuthOtpScreenDecomposeComponent(componentContext) {
    private val internalOtpType = otpType.toInternalAuthOtpType()

    private val viewModel = viewModelWithFactoryWithoutRemember(internalOtpType) {
        viewModelFactory(internalOtpType)
    }


    @Composable
    override fun Render(modifier: Modifier) {
        val state by viewModel.getState().collectAsState()
        AuthOtpScreenComposable(
            modifier = modifier,
            otpType = internalOtpType,
            onConfirm = {},
            onBack = onBack::invoke,
            authOtpScreenState = state,
            onResend = {},
            otpCodeFieldComposable = {},
        )
    }

    @Inject
    @ContributesBinding(AppGraph::class, AuthOtpScreenDecomposeComponent.Factory::class)
    class Factory(
        private val factory: (
            componentContext: ComponentContext,
            onBack: DecomposeOnBackParameter,
            otpType: AuthOtpType
        ) -> AuthOtpScreenDecomposeComponentImpl
    ) : AuthOtpScreenDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            onBack: DecomposeOnBackParameter,
            otpType: AuthOtpType
        ) = factory(componentContext, onBack, otpType)
    }
}