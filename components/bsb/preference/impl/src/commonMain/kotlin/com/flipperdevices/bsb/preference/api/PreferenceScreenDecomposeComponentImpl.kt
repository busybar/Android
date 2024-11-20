package com.flipperdevices.bsb.preference.api

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.bsb.preference.composable.SettingsScreenComposable
import com.flipperdevices.bsb.preference.model.SettingsEnum
import com.flipperdevices.bsb.preference.viewmodel.SettingsViewModel
import com.flipperdevices.core.di.AppGraph
import com.flipperdevices.core.ui.lifecycle.viewModelWithFactory
import com.flipperdevices.ui.decompose.DecomposeOnBackParameter
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@Inject
class PreferenceScreenDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
    @Assisted private val onBackParameter: DecomposeOnBackParameter,
    private val settingsViewModelFactory: () -> SettingsViewModel
) : PreferenceScreenDecomposeComponent(componentContext) {
    @Composable
    override fun Render() {
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
                //rootNavigationApi.openRootScreen()
            }
        )
    }

    @Inject
    @ContributesBinding(AppGraph::class, PreferenceScreenDecomposeComponent.Factory::class)
    class Factory(
        private val factory: (
            componentContext: ComponentContext,
            onBackParameter: DecomposeOnBackParameter
        ) -> PreferenceScreenDecomposeComponentImpl
    ) : PreferenceScreenDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            onBackParameter: DecomposeOnBackParameter
        ) = factory(componentContext, onBackParameter)
    }
}