package com.flipperdevices.bsb.auth.otp.screen.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.flipperdevices.bsb.auth.otp.element.api.AuthOtpElementDecomposeComponent
import com.flipperdevices.bsb.auth.otp.element.model.OtpElementState
import com.flipperdevices.bsb.auth.otp.screen.composable.AuthOtpScreenComposable
import com.flipperdevices.bsb.auth.otp.screen.composable.OtpCodeFieldComposable
import com.flipperdevices.bsb.auth.otp.screen.model.AuthOtpScreenState
import com.flipperdevices.bsb.auth.otp.screen.model.AuthOtpType
import com.flipperdevices.bsb.auth.otp.screen.model.InternalAuthOtpType
import com.flipperdevices.bsb.auth.otp.screen.model.toInternalAuthOtpType
import com.flipperdevices.bsb.auth.otp.screen.viewmodel.AuthOtpScreenViewModel
import com.flipperdevices.core.di.AppGraph
import com.flipperdevices.core.ui.lifecycle.viewModelWithFactoryWithoutRemember
import com.flipperdevices.ui.decompose.DecomposeOnBackParameter
import com.flipperdevices.ui.decompose.ElementDecomposeComponent
import kotlinx.datetime.Instant
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@Inject
class AuthOtpScreenDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
    @Assisted private val onBack: DecomposeOnBackParameter,
    @Assisted otpType: AuthOtpType,
    @Assisted onOtpComplete: suspend (String) -> Unit,
    private val viewModelFactory: (
        otpType: InternalAuthOtpType,
        onOtpComplete: suspend (String) -> Unit,
        codeExpiryTimeMs: Long,
    ) -> AuthOtpScreenViewModel,
    otpCodeElementDecomposeComponentFactory: AuthOtpElementDecomposeComponent.Factory
) : AuthOtpScreenDecomposeComponent(componentContext) {
    private val internalOtpType = otpType.toInternalAuthOtpType()

    private val viewModel = viewModelWithFactoryWithoutRemember(internalOtpType) {
        viewModelFactory(
            internalOtpType,
            onOtpComplete,
            otpType.codeExpiryTime.toEpochMilliseconds()
        )
    }

    private val otpCodeElementDecomposeComponent = otpCodeElementDecomposeComponentFactory(
        componentContext = childContext("otpCodeElement")
    )


    @Composable
    override fun Render(modifier: Modifier) {
        val state by viewModel.getState().collectAsState()
        val otpCode by otpCodeElementDecomposeComponent.getOtpCodeState().collectAsState()
        val timerState by viewModel.getTimerState().collectAsState()

        AuthOtpScreenComposable(
            modifier = modifier,
            otpType = internalOtpType,
            onConfirm = {
                viewModel.onCodeApply(otpCode)
            },
            onBack = onBack::invoke,
            authOtpScreenState = state,
            onResend = viewModel::onReset,
            otpCodeFieldComposable = { otpCodeModifier ->
                OtpCodeFieldComposable(
                    modifier = otpCodeModifier,
                    otpCodeFieldComposable = { otpCodeModifier, otpState ->
                        otpCodeElementDecomposeComponent.Render(otpCodeModifier, otpState)
                    },
                    otpScreenState = state,
                    timerState = timerState,
                    otpType = internalOtpType
                )
            },
        )
    }

    @Inject
    @ContributesBinding(AppGraph::class, AuthOtpScreenDecomposeComponent.Factory::class)
    class Factory(
        private val factory: (
            componentContext: ComponentContext,
            onBack: DecomposeOnBackParameter,
            otpType: AuthOtpType,
            onOtpComplete: suspend (String) -> Unit
        ) -> AuthOtpScreenDecomposeComponentImpl
    ) : AuthOtpScreenDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            onBack: DecomposeOnBackParameter,
            otpType: AuthOtpType,
            onOtpComplete: suspend (String) -> Unit
        ) = factory(componentContext, onBack, otpType, onOtpComplete)
    }
}