package com.flipperdevices.busybar.auth.login.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.flipperdevices.busybar.auth.login.model.LoginNavigationConfig
import com.flipperdevices.busybar.core.decompose.DecomposeComponent
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class LoginDecomposeComponent(
    @Assisted componentContext: ComponentContext,
    private val loginPasswordDecomposeComponent: (ComponentContext) -> LoginPasswordDecomposeComponent
) : DecomposeComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<LoginNavigationConfig>()

    private val stack = childStack(
        source = navigation,
        serializer = LoginNavigationConfig.serializer(),
        initialConfiguration = LoginNavigationConfig.Password,
        childFactory = ::child,
        handleBackButton = true
    )

    @Composable
    override fun Render(modifier: Modifier) {
        val childStack by stack.subscribeAsState()

        childStack.active.instance.Render(modifier)
    }

    private fun child(
        config: LoginNavigationConfig,
        componentContext: ComponentContext
    ): DecomposeComponent = when (config) {
        LoginNavigationConfig.Password -> loginPasswordDecomposeComponent(componentContext)
    }
}