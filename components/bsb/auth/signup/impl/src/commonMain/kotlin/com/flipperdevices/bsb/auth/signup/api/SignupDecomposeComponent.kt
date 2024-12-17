package com.flipperdevices.bsb.auth.signup.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pushToFront
import com.flipperdevices.bsb.auth.confirmpassword.api.AuthConfirmPasswordScreenDecomposeComponent
import com.flipperdevices.bsb.auth.confirmpassword.model.ConfirmPasswordType
import com.flipperdevices.bsb.auth.otp.screen.api.AuthOtpScreenDecomposeComponent
import com.flipperdevices.bsb.auth.otp.screen.model.AuthOtpType
import com.flipperdevices.bsb.auth.signup.model.SignupNavigationConfig
import com.flipperdevices.core.di.AppGraph
import com.flipperdevices.ui.decompose.DecomposeComponent
import com.flipperdevices.ui.decompose.DecomposeOnBackParameter
import com.flipperdevices.ui.decompose.popOr
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@Inject
class SignupDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
    @Assisted private val onBack: DecomposeOnBackParameter,
    @Assisted private val email: String,
    @Assisted private val preFilledPassword: String?,
    @Assisted private val onComplete: () -> Unit,
    private val signupScreenDecomposeComponent: (
        componentContext: ComponentContext,
        onBack: DecomposeOnBackParameter,
        email: String,
        onContinue: () -> Unit
    ) -> SignupScreenDecomposeComponentImpl,
    private val otpScreenDecomposeComponent: AuthOtpScreenDecomposeComponent.Factory,
    private val confirmPasswordScreenDecomposeComponent: AuthConfirmPasswordScreenDecomposeComponent.Factory
) : SignupDecomposeComponent<SignupNavigationConfig>(),
    ComponentContext by componentContext {
    override val stack = childStack(
        source = navigation,
        serializer = SignupNavigationConfig.serializer(),
        initialConfiguration = SignupNavigationConfig.Main,
        handleBackButton = true,
        childFactory = ::child,
    )

    private fun child(
        config: SignupNavigationConfig,
        componentContext: ComponentContext
    ): DecomposeComponent = when (config) {
        SignupNavigationConfig.Main -> signupScreenDecomposeComponent(
            componentContext,
            { navigation.popOr(onBack::invoke) },
            email,
            { navigation.pushToFront(SignupNavigationConfig.ConfirmEmail) },
        )

        SignupNavigationConfig.ConfirmEmail -> otpScreenDecomposeComponent(
            componentContext = componentContext,
            onBack = { navigation.popOr(onBack::invoke) },
            otpType = AuthOtpType.VerifyEmail(
                email = email
            ),
            onOtpComplete = { otpCode ->
                navigation.pushToFront(SignupNavigationConfig.EnterPassword(code = otpCode))
            }
        )

        is SignupNavigationConfig.EnterPassword -> confirmPasswordScreenDecomposeComponent(
            componentContext = componentContext,
            type = ConfirmPasswordType.SignUpPassword(
                email = email,
                otpCode = config.code,
                preFilledPassword = preFilledPassword
            ),
            onBackParameter = { navigation.popOr(onBack::invoke) },
            onComplete = onComplete
        )
    }

    @Inject
    @ContributesBinding(AppGraph::class, SignupDecomposeComponent.Factory::class)
    class Factory(
        private val factory: (
            componentContext: ComponentContext,
            onBack: DecomposeOnBackParameter,
            email: String,
            preFilledPassword: String?,
            onComplete: () -> Unit,
        ) -> SignupDecomposeComponentImpl
    ) : SignupDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            onBack: DecomposeOnBackParameter,
            email: String,
            preFilledPassword: String?,
            onComplete: () -> Unit,
        ) = factory(componentContext, onBack, email, preFilledPassword, onComplete)
    }
}