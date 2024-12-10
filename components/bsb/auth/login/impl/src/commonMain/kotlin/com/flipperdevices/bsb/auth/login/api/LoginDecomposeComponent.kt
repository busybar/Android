package com.flipperdevices.bsb.auth.login.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pushToFront
import com.flipperdevices.bsb.auth.login.model.LoginNavigationConfig
import com.flipperdevices.bsb.auth.otp.screen.api.AuthOtpScreenDecomposeComponent
import com.flipperdevices.bsb.auth.otp.screen.model.AuthOtpType
import com.flipperdevices.core.di.AppGraph
import com.flipperdevices.ui.decompose.DecomposeComponent
import com.flipperdevices.ui.decompose.DecomposeOnBackParameter
import com.flipperdevices.ui.decompose.popOr
import kotlinx.datetime.Instant
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@Inject
class LoginDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
    @Assisted private val onBack: DecomposeOnBackParameter,
    @Assisted private val email: String,
    @Assisted private val onComplete: () -> Unit,
    private val loginScreenDecomposeComponent: (
        componentContext: ComponentContext,
        onBack: DecomposeOnBackParameter,
        email: String,
        onComplete: () -> Unit,
        onForgetPassword: (email: String, codeExpiryTime: Instant) -> Unit
    ) -> LoginScreenDecomposeComponentImpl,
    private val otpScreenDecomposeComponent: AuthOtpScreenDecomposeComponent.Factory
) : LoginDecomposeComponent<LoginNavigationConfig>(),
    ComponentContext by componentContext {
    override val stack = childStack(
        source = navigation,
        serializer = LoginNavigationConfig.serializer(),
        initialConfiguration = LoginNavigationConfig.Password,
        handleBackButton = true,
        childFactory = ::child,
    )

    private fun child(
        config: LoginNavigationConfig,
        componentContext: ComponentContext
    ): DecomposeComponent = when (config) {
        LoginNavigationConfig.Password -> loginScreenDecomposeComponent(
            componentContext,
            { navigation.popOr(onBack::invoke) },
            email,
            onComplete,
            { email, codeExpiryTime ->
                navigation.pushToFront(LoginNavigationConfig.ResetPassword(email, codeExpiryTime))
            }
        )

        is LoginNavigationConfig.ResetPassword -> otpScreenDecomposeComponent(
            componentContext = componentContext,
            onBack = { navigation.popOr(onBack::invoke) },
            otpType = AuthOtpType.ForgotPassword(
                email = config.email,
                codeExpiryTime = config.codeExpiryTime
            ),
            onOtpComplete = {}
        )
    }

    @Inject
    @ContributesBinding(AppGraph::class, LoginDecomposeComponent.Factory::class)
    class Factory(
        private val factory: (
            componentContext: ComponentContext,
            onBack: DecomposeOnBackParameter,
            email: String,
            onComplete: () -> Unit,
        ) -> LoginDecomposeComponentImpl
    ) : LoginDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            onBack: DecomposeOnBackParameter,
            email: String,
            onComplete: () -> Unit,
        ) = factory(componentContext, onBack, email, onComplete)
    }
}