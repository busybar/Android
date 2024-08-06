package com.flipperdevices.busybar.bottombar.api

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.flipperdevices.busybar.apps.api.AppsDecomposeComponent
import com.flipperdevices.busybar.core.decompose.DecomposeComponent
import com.flipperdevices.busybar.bottombar.composable.ComposableBottomBarScreen
import com.flipperdevices.busybar.bottombar.config.BottomBarConfig
import com.flipperdevices.busybar.bottombar.config.BottomBarEnum
import com.flipperdevices.busybar.device.api.DeviceDecomposeComponent
import com.flipperdevices.busybar.login.api.LogInDecomposeComponent
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class BottomBarDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
    private val logInDecomposeComponentFactory: (componentContext: ComponentContext) -> LogInDecomposeComponent,
    private val deviceDecomposeComponentFactory: (componentContext: ComponentContext) -> DeviceDecomposeComponent,
    private val appsDecomposeComponentFactory: (componentContext: ComponentContext) -> AppsDecomposeComponent,
) : DecomposeComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<BottomBarConfig>()

    private val stack: Value<ChildStack<BottomBarConfig, DecomposeComponent>> =
        childStack(
            source = navigation,
            serializer = BottomBarConfig.serializer(),
            initialConfiguration = BottomBarConfig.Device,
            childFactory = ::child,
        )

    @Composable
    @Suppress("NonSkippableComposable")
    override fun Render(modifier: Modifier) {
        val childStack by stack.subscribeAsState()

        ComposableBottomBarScreen(
            childStack = childStack,
            modifier = Modifier.fillMaxSize(),
            onSelect = {
                when (it) {
                    BottomBarEnum.LOGIN -> navigation.bringToFront(BottomBarConfig.Login)
                    BottomBarEnum.DEVICE -> navigation.bringToFront(BottomBarConfig.Device)
                    BottomBarEnum.APPS -> navigation.bringToFront(BottomBarConfig.Apps)
                }
            }
        )
    }


    private fun child(
        config: BottomBarConfig,
        componentContext: ComponentContext
    ): DecomposeComponent = when (config) {
        BottomBarConfig.Device -> deviceDecomposeComponentFactory(
            componentContext
        )

        BottomBarConfig.Login -> logInDecomposeComponentFactory(
            componentContext
        )

        is BottomBarConfig.Apps -> appsDecomposeComponentFactory(
            componentContext
        )
    }
}