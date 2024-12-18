package com.flipperdevices.bsb.auth.otp.screen.api

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.flipperdevices.bsb.auth.otp.element.api.AuthOtpElementDecomposeComponent
import com.flipperdevices.bsb.auth.otp.screen.composable.AuthOtpScreenComposable
import com.flipperdevices.bsb.auth.otp.screen.composable.OtpCodeFieldComposable
import com.flipperdevices.bsb.auth.otp.screen.model.AuthOtpType
import com.flipperdevices.bsb.auth.otp.screen.model.InternalAuthOtpType
import com.flipperdevices.bsb.auth.otp.screen.model.toInternalAuthOtpType
import com.flipperdevices.bsb.auth.otp.screen.viewmodel.AuthOtpScreenViewModel
import com.flipperdevices.bsb.deeplink.model.Deeplink
import com.flipperdevices.core.di.AppGraph
import com.flipperdevices.core.ui.lifecycle.viewModelWithFactory
import com.flipperdevices.ui.decompose.DecomposeOnBackParameter
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@OptIn(ExperimentalFoundationApi::class)
@Inject
class AuthOtpScreenDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
    @Assisted private val onBack: DecomposeOnBackParameter,
    @Assisted otpType: AuthOtpType,
    @Assisted deeplink: Deeplink.Root.Auth.VerifyEmailLink?,
    @Assisted onOtpComplete: suspend (String) -> Unit,
    private val viewModelFactory: (
        otpType: InternalAuthOtpType,
        onOtpComplete: suspend (String) -> Unit,
        onFocus: suspend () -> Unit,
        prefilledCode: String?
    ) -> AuthOtpScreenViewModel,
    otpCodeElementDecomposeComponentFactory: AuthOtpElementDecomposeComponent.Factory
) : AuthOtpScreenDecomposeComponent(componentContext) {
    private val internalOtpType = otpType.toInternalAuthOtpType()

    private val otpCodeElementDecomposeComponent = otpCodeElementDecomposeComponentFactory(
        componentContext = childContext("otpCodeElement"),
        preFilledCode = deeplink?.otpCode
    )

    private val viewModel = viewModelWithFactory(
        internalOtpType to onOtpComplete to otpType to deeplink
    ) {
        viewModelFactory(
            internalOtpType,
            onOtpComplete,
            { otpCodeElementDecomposeComponent.onFocus() },
            deeplink?.otpCode
        )
    }

    @Composable
    override fun Render(modifier: Modifier) {
        val state by viewModel.getState().collectAsState()
        val otpCode by otpCodeElementDecomposeComponent.getOtpCodeState().collectAsState()
        val expiryState by viewModel.getExpiryTimerState().collectAsState()
        val bringIntoViewRequester = remember { BringIntoViewRequester() }
        val scope = rememberCoroutineScope()

        AuthOtpScreenComposable(
            modifier = modifier
                .fillMaxSize(),
            otpType = internalOtpType,
            onConfirm = {
                viewModel.onCodeApply(otpCode)
            },
            onBack = onBack::invoke,
            authOtpScreenState = state,
            onResend = viewModel::onReset,
            otpCodeFieldComposable = { otpCodeFieldModifier ->
                OtpCodeFieldComposable(
                    modifier = otpCodeFieldModifier,
                    otpCodeFieldComposable = { otpCodeModifier, otpState ->
                        otpCodeElementDecomposeComponent.Render(
                            otpCodeModifier,
                            otpState,
                            onFocus = { scope.launch { bringIntoViewRequester.bringIntoView() } }
                        )
                    },
                    otpScreenState = state,
                    otpType = internalOtpType,
                    expiryState = expiryState
                )
            },
            bringIntoViewRequester = bringIntoViewRequester
        )
    }

    override fun handleDeeplink(deeplink: Deeplink.Root.Auth.VerifyEmailLink) {
        otpCodeElementDecomposeComponent.insertOtp(deeplink.otpCode)
        viewModel.onCodeApply(deeplink.otpCode)
    }

    @Inject
    @ContributesBinding(AppGraph::class, AuthOtpScreenDecomposeComponent.Factory::class)
    class Factory(
        private val factory: (
            componentContext: ComponentContext,
            onBack: DecomposeOnBackParameter,
            otpType: AuthOtpType,
            deeplink: Deeplink.Root.Auth.VerifyEmailLink?,
            onOtpComplete: suspend (String) -> Unit
        ) -> AuthOtpScreenDecomposeComponentImpl
    ) : AuthOtpScreenDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            onBack: DecomposeOnBackParameter,
            otpType: AuthOtpType,
            deeplink: Deeplink.Root.Auth.VerifyEmailLink?,
            onOtpComplete: suspend (String) -> Unit,
        ) = factory(componentContext, onBack, otpType, deeplink, onOtpComplete)
    }
}
