package com.flipperdevices.busybar.root.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushToFront
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import com.flipperdevices.busybar.apps.api.AppsDecomposeComponent
import com.flipperdevices.busybar.apps.composable.apps.BusyBarApp
import com.flipperdevices.busybar.core.decompose.DecomposeComponent
import com.flipperdevices.busybar.core.decompose.DecomposeOnBackParameter
import com.flipperdevices.busybar.core.theme.DarkModeSingleton
import com.flipperdevices.busybar.device.api.DeviceDecomposeComponent
import com.flipperdevices.busybar.root.config.RootScreenConfig
import com.flipperdevices.busybar.search.api.SearchDecomposeComponent
import com.flipperdevices.busybar.settings.api.SettingsDecomposeComponent
import com.flipperdevices.busybar.settings.model.SettingsEnum
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import kotlinx.coroutines.flow.update
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class RootDecomposeComponent(
    @Assisted componentContext: ComponentContext,
    private val deviceDecomposeComponentFactory: (
        componentContext: ComponentContext,
        navigationApi: RootNavigationApi
    ) -> DeviceDecomposeComponent,
    private val appsDecomposeComponentFactory: (
        componentContext: ComponentContext,
        navigationApi: RootNavigationApi,
        onBack: DecomposeOnBackParameter
    ) -> AppsDecomposeComponent,
    private val searchDecomposeComponentFactory: (
        componentContext: ComponentContext,
        navigationApi: RootNavigationApi,
    ) -> SearchDecomposeComponent,
    private val settings: Settings,
    private val settingsDecomposeComponentFactory: (
        componentContext: ComponentContext,
        onBackParameter: DecomposeOnBackParameter,
        rootNavigationApi: RootNavigationApi
    ) -> SettingsDecomposeComponent
) : DecomposeComponent, RootNavigationApi, ComponentContext by componentContext {
    private val navigation = StackNavigation<RootScreenConfig>()

    init {
        DarkModeSingleton.darkMode.update { settings[SettingsEnum.DARK_THEME.key, false] }
        DarkModeSingleton.devMode.update { settings[SettingsEnum.DEV_MODE.key, false] }
    }

    private val stack: Value<ChildStack<RootScreenConfig, DecomposeComponent>> =
        childStack(
            source = navigation,
            serializer = RootScreenConfig.serializer(),
            initialConfiguration = getRootScreen(),
            childFactory = ::child,
            handleBackButton = true
        )

    @Composable
    override fun Render(modifier: Modifier) {
        val childStack by stack.subscribeAsState()

        childStack.active.instance.Render(modifier)
    }

    override fun push(config: RootScreenConfig) {
        navigation.pushToFront(config)
    }

    override fun openRootScreen() {
        navigation.replaceAll(getRootScreen())
    }

    override fun onAppSelected(barApp: BusyBarApp) {
        val deviceStackItem = stack.value.items.find { it.configuration == RootScreenConfig.DEVICE }
        val deviceComponent = deviceStackItem?.instance as? DeviceDecomposeComponent ?: return
        deviceComponent.onAppSelected(barApp)
    }

    private fun child(
        config: RootScreenConfig,
        componentContext: ComponentContext
    ): DecomposeComponent = when (config) {
        RootScreenConfig.SEARCH -> searchDecomposeComponentFactory(
            componentContext,
            this
        )

        RootScreenConfig.DEVICE -> deviceDecomposeComponentFactory(
            componentContext,
            this
        )

        RootScreenConfig.APPS -> appsDecomposeComponentFactory(
            componentContext,
            this,
            navigation::pop
        )

        RootScreenConfig.SETTINGS -> settingsDecomposeComponentFactory(
            componentContext,
            navigation::pop,
            this
        )
    }

    private fun getRootScreen(): RootScreenConfig {
        val skipSearch = settings[SettingsEnum.SKIP_SEARCH.key, false]
        return if (skipSearch) {
            RootScreenConfig.DEVICE
        } else RootScreenConfig.SEARCH
    }
}