package com.flipperdevices.bsb.auth.main.api

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushToFront
import com.flipperdevices.bsb.auth.login.api.LoginDecomposeComponent
import com.flipperdevices.bsb.auth.main.model.AuthRootNavigationConfig
import com.flipperdevices.bsb.auth.signup.api.SignupDecomposeComponent
import com.flipperdevices.bsb.auth.within.oauth.api.OAuthWebViewDecomposeComponent
import com.flipperdevices.bsb.auth.within.oauth.model.OAuthProvider
import com.flipperdevices.bsb.core.theme.LocalPallet
import com.flipperdevices.bsb.deeplink.model.Deeplink
import com.flipperdevices.core.di.AppGraph
import com.flipperdevices.core.log.warn
import com.flipperdevices.ui.decompose.DecomposeComponent
import com.flipperdevices.ui.decompose.DecomposeOnBackParameter
import com.flipperdevices.ui.decompose.findChildByConfig
import com.flipperdevices.ui.decompose.findComponentByConfig
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@Inject
class AuthDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
    @Assisted private val onBackParameter: DecomposeOnBackParameter,
    @Assisted deeplink: Deeplink.Root.Auth?,
    private val mainScreenDecomposeComponent: (
        ComponentContext,
        StackNavigation<AuthRootNavigationConfig>,
        onComplete: () -> Unit,
        deeplink: Deeplink.Root.Auth?,
        openWebView: (OAuthProvider) -> Unit,
    ) -> MainScreenDecomposeComponentImpl,
    private val loginDecomposeComponentFactory: LoginDecomposeComponent.Factory,
    private val signupDecomposeComponentFactory: SignupDecomposeComponent.Factory,
    private val oAuthWebViewDecomposeComponentFactory: OAuthWebViewDecomposeComponent.Factory
) : AuthDecomposeComponent<AuthRootNavigationConfig>(),
    ComponentContext by componentContext {

    override val stack = childStack(
        source = navigation,
        serializer = AuthRootNavigationConfig.serializer(),
        initialConfiguration = AuthRootNavigationConfig.AuthRoot(deeplink),
        handleBackButton = true,
        childFactory = ::child,
    )

    private fun child(
        config: AuthRootNavigationConfig,
        componentContext: ComponentContext
    ): DecomposeComponent = when (config) {
        is AuthRootNavigationConfig.AuthRoot -> mainScreenDecomposeComponent(
            componentContext,
            navigation,
            onBackParameter::invoke,
            config.deeplink,
            { oauth -> navigation.pushToFront(AuthRootNavigationConfig.WebView(oauth)) }
        )

        is AuthRootNavigationConfig.LogIn -> loginDecomposeComponentFactory(
            componentContext,
            onBack = navigation::pop,
            email = config.email,
            onComplete = onBackParameter::invoke
        )

        AuthRootNavigationConfig.SignUp -> signupDecomposeComponentFactory(
            componentContext
        )

        is AuthRootNavigationConfig.WebView -> oAuthWebViewDecomposeComponentFactory(
            componentContext,
            config.oAuthProvider,
            onComplete = { token ->
                navigation.pop()
                when (config.oAuthProvider) {
                    OAuthProvider.APPLE -> handleDeeplink(Deeplink.Root.Auth.OAuth.Apple(token))
                    OAuthProvider.MICROSOFT -> {
                        handleDeeplink(Deeplink.Root.Auth.OAuth.Microsoft(token))
                    }
                }
            }
        )
    }

    @Composable
    override fun Render(modifier: Modifier) {
        super.Render(
            modifier
                .background(LocalPallet.current.surface.primary)
        )
    }

    override fun handleDeeplink(deeplink: Deeplink.Root.Auth) {
        val child = stack.findChildByConfig(AuthRootNavigationConfig.AuthRoot::class)
        val instance = child?.instance
        if (child == null || instance !is MainScreenDecomposeComponentImpl) {
            warn { "Bottom bar component is not exist in stack, but first pair screen already passed" }
            navigation.bringToFront(AuthRootNavigationConfig.AuthRoot(deeplink))
        } else {
            navigation.bringToFront(child.configuration)
            instance.handleDeeplink(deeplink)
        }
    }

    @Inject
    @ContributesBinding(AppGraph::class, AuthDecomposeComponent.Factory::class)
    class Factory(
        private val factory: (
            componentContext: ComponentContext,
            onBackParameter: DecomposeOnBackParameter,
            deeplink: Deeplink.Root.Auth?
        ) -> AuthDecomposeComponentImpl
    ) : AuthDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            onBackParameter: DecomposeOnBackParameter,
            deeplink: Deeplink.Root.Auth?
        ) = factory(componentContext, onBackParameter, deeplink)
    }
}