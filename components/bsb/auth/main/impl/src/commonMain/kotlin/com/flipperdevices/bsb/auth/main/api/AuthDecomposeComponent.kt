package com.flipperdevices.bsb.auth.main.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.flipperdevices.bsb.auth.main.model.AuthRootNavigationConfig
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
        StackNavigation<AuthRootNavigationConfig>
    ) -> MainScreenDecomposeComponentImpl
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
            navigation
        )

        is AuthRootNavigationConfig.LogIn -> TODO()
        AuthRootNavigationConfig.SignUp -> TODO()
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