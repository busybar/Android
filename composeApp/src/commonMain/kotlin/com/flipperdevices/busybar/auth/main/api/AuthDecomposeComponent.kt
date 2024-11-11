package com.flipperdevices.busybar.auth.main.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.flipperdevices.busybar.core.decompose.DecomposeComponent
import com.flipperdevices.busybar.auth.common.model.AuthRootNavigationConfig
import com.flipperdevices.busybar.auth.login.api.LoginDecomposeComponent
import com.flipperdevices.busybar.auth.signup.api.SignUpDecomposeComponent
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class AuthDecomposeComponent(
    @Assisted componentContext: ComponentContext,
    private val authMainDecomposeComponentFactory: (
        componentContext: ComponentContext,
        authNavigation: StackNavigation<AuthRootNavigationConfig>
    ) -> AuthMainDecomposeComponent,
    private val logInDecomposeComponentFactory: (ComponentContext) -> LoginDecomposeComponent,
    private val signUpDecomposeComponentFactory: (ComponentContext) -> SignUpDecomposeComponent
) : DecomposeComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<AuthRootNavigationConfig>()

    private val stack = childStack(
        source = navigation,
        serializer = AuthRootNavigationConfig.serializer(),
        initialConfiguration = AuthRootNavigationConfig.Main,
        childFactory = ::child,
        handleBackButton = true
    )

    @Composable
    override fun Render(modifier: Modifier) {
        val childStack by stack.subscribeAsState()

        childStack.active.instance.Render(modifier)
    }

    private fun child(
        config: AuthRootNavigationConfig,
        componentContext: ComponentContext
    ): DecomposeComponent = when (config) {
        AuthRootNavigationConfig.Main -> authMainDecomposeComponentFactory(
            componentContext,
            navigation
        )

        AuthRootNavigationConfig.Login -> logInDecomposeComponentFactory(componentContext)
        AuthRootNavigationConfig.SignUp -> signUpDecomposeComponentFactory(componentContext)
    }
}