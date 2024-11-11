package com.flipperdevices.busybar.auth.login.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.flipperdevices.busybar.auth.signup.model.SignUpNavigationConfig
import com.flipperdevices.busybar.auth.signup.api.SignUpPasswordDecomposeComponent
import com.flipperdevices.busybar.core.decompose.DecomposeComponent
import com.flipperdevices.busybar.core.decompose.DecomposeOnBackParameter
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class LoginDecomposeComponent(
    @Assisted componentContext: ComponentContext,
    @Assisted private val onBack: DecomposeOnBackParameter,
    @Assisted private val email: String,
    private val loginPasswordDecomposeComponent: (
        componentContext: ComponentContext,
        onBack: DecomposeOnBackParameter,
        email: String
    ) -> LoginPasswordDecomposeComponent
) : DecomposeComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<SignUpNavigationConfig>()

    private val stack = childStack(
        source = navigation,
        serializer = SignUpNavigationConfig.serializer(),
        initialConfiguration = SignUpNavigationConfig.Password,
        childFactory = ::child,
        handleBackButton = true
    )

    @Composable
    override fun Render(modifier: Modifier) {
        val childStack by stack.subscribeAsState()

        childStack.active.instance.Render(modifier)
    }

    private fun child(
        config: SignUpNavigationConfig,
        componentContext: ComponentContext
    ): DecomposeComponent = when (config) {
        SignUpNavigationConfig.Password -> loginPasswordDecomposeComponent(
            componentContext,
            onBack,
            email
        )
    }
}