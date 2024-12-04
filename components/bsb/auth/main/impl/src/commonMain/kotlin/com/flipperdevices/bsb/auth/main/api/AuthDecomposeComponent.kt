package com.flipperdevices.bsb.auth.main.api

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.flipperdevices.bsb.auth.login.api.LoginDecomposeComponent
import com.flipperdevices.bsb.auth.main.model.AuthRootNavigationConfig
import com.flipperdevices.bsb.auth.signup.api.SignupDecomposeComponent
import com.flipperdevices.bsb.core.theme.LocalPallet
import com.flipperdevices.core.di.AppGraph
import com.flipperdevices.ui.decompose.DecomposeComponent
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@Inject
class AuthDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
    private val mainScreenDecomposeComponent: (
        ComponentContext,
        StackNavigation<AuthRootNavigationConfig>,
        onComplete: () -> Unit
    ) -> MainScreenDecomposeComponentImpl,
    private val loginDecomposeComponentFactory: LoginDecomposeComponent.Factory,
    private val signupDecomposeComponentFactory: SignupDecomposeComponent.Factory
) : AuthDecomposeComponent(),
    ComponentContext by componentContext {
    override val stack = childStack(
        source = navigation,
        serializer = AuthRootNavigationConfig.serializer(),
        initialConfiguration = AuthRootNavigationConfig.AuthRoot,
        handleBackButton = true,
        childFactory = ::child,
    )

    private fun child(
        config: AuthRootNavigationConfig,
        componentContext: ComponentContext
    ): DecomposeComponent = when (config) {
        AuthRootNavigationConfig.AuthRoot -> mainScreenDecomposeComponent(
            componentContext,
            navigation,
            {}
        )

        is AuthRootNavigationConfig.LogIn -> loginDecomposeComponentFactory(
            componentContext,
            onBack = navigation::pop,
            email = config.email,
            onComplete = {}
        )

        AuthRootNavigationConfig.SignUp -> signupDecomposeComponentFactory(
            componentContext
        )
    }

    @Composable
    override fun Render(modifier: Modifier) {
        super.Render(
            modifier
                .background(LocalPallet.current.surface.primary)
        )
    }

    @Inject
    @ContributesBinding(AppGraph::class, AuthDecomposeComponent.Factory::class)
    class Factory(
        private val factory: (
            componentContext: ComponentContext
        ) -> AuthDecomposeComponentImpl
    ) : AuthDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext
        ) = factory(componentContext)
    }
}