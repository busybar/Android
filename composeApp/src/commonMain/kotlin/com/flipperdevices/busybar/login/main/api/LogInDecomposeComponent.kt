package com.flipperdevices.busybar.login.main.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.flipperdevices.busybar.core.decompose.DecomposeComponent
import com.flipperdevices.busybar.login.common.model.LogInNavigationConfig
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class LogInDecomposeComponent(
    @Assisted componentContext: ComponentContext,
    private val logInMainDecomposeComponentFactory: (ComponentContext) -> LogInMainDecomposeComponent
) : DecomposeComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<LogInNavigationConfig>()

    private val stack = childStack(
        source = navigation,
        serializer = LogInNavigationConfig.serializer(),
        initialConfiguration = LogInNavigationConfig.Main,
        childFactory = ::child,
        handleBackButton = true
    )

    @Composable
    override fun Render(modifier: Modifier) {
        val childStack by stack.subscribeAsState()

        childStack.active.instance.Render(modifier)
    }

    private fun child(
        config: LogInNavigationConfig,
        componentContext: ComponentContext
    ): DecomposeComponent = when (config) {
        LogInNavigationConfig.Main -> logInMainDecomposeComponentFactory(componentContext)
    }
}