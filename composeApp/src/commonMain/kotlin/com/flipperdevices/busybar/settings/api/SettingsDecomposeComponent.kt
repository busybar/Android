package com.flipperdevices.busybar.settings.api

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.busybar.core.decompose.DecomposeComponent
import com.flipperdevices.busybar.core.decompose.DecomposeOnBackParameter
import com.flipperdevices.busybar.core.decompose.viewModelWithFactory
import com.flipperdevices.busybar.root.api.RootNavigationApi
import com.flipperdevices.busybar.settings.viewmodel.SettingsViewModel
import com.flipperdevices.busybar.settings.composable.SettingsScreenComposable
import com.flipperdevices.busybar.settings.model.SettingsEnum
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class SettingsDecomposeComponent(
    @Assisted componentContext: ComponentContext,
    @Assisted private val onBackParameter: DecomposeOnBackParameter,
    @Assisted private val rootNavigationApi: RootNavigationApi,
    private val settingsViewModelFactory: () -> SettingsViewModel
) : DecomposeComponent, ComponentContext by componentContext {


    @Composable
    override fun Render(modifier: Modifier) {
        val viewModel = viewModelWithFactory(null) {
            settingsViewModelFactory()
        }

        val settingsState by viewModel.getState().collectAsState()
        SettingsScreenComposable(
            modifier = Modifier.systemBarsPadding()
                .statusBarsPadding(),
            settingsState = settingsState,
            onBack = onBackParameter::invoke,
            onChange = viewModel::onChange,
            onForgetDevice = {
                viewModel.onChange(SettingsEnum.SKIP_SEARCH, false)
                rootNavigationApi.openRootScreen()
            }
        )
    }
}