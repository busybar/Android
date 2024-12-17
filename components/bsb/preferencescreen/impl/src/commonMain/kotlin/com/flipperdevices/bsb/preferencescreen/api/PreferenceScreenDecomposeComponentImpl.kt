package com.flipperdevices.bsb.preferencescreen.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.bsb.preference.api.ThemeStatusBarIconStyleProvider
import com.flipperdevices.bsb.preferencescreen.composable.PreferenceScreenComposable
import com.flipperdevices.bsb.preferencescreen.viewmodel.PreferenceScreenViewModel
import com.flipperdevices.bsb.root.api.LocalRootNavigation
import com.flipperdevices.core.di.AppGraph
import com.flipperdevices.core.ui.lifecycle.viewModelWithFactory
import com.flipperdevices.ui.decompose.DecomposeOnBackParameter
import com.flipperdevices.ui.decompose.statusbar.StatusBarIconStyleProvider
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@Inject
class PreferenceScreenDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
    @Assisted private val onBackParameter: DecomposeOnBackParameter,
    private val statusBarIconStyleProvider: ThemeStatusBarIconStyleProvider,
    private val viewModelFactory: () -> PreferenceScreenViewModel
) : PreferenceScreenDecomposeComponent(componentContext),
    StatusBarIconStyleProvider by statusBarIconStyleProvider {

    private val viewModel = viewModelWithFactory(null) {
        viewModelFactory()
    }


    @Composable
    override fun Render(modifier: Modifier) {
        val rootNavigation = LocalRootNavigation.current

        val state by viewModel.getState().collectAsState()
        PreferenceScreenComposable(
            modifier,
            onBack = onBackParameter::invoke,
            screenState = state,
            onAction = { viewModel.onAction(it, rootNavigation) }
        )
    }

    @Inject
    @ContributesBinding(AppGraph::class, PreferenceScreenDecomposeComponent.Factory::class)
    class Factory(
        private val factory: (
            componentContext: ComponentContext,
            onBackParameter: DecomposeOnBackParameter,
        ) -> PreferenceScreenDecomposeComponentImpl
    ) : PreferenceScreenDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            onBackParameter: DecomposeOnBackParameter
        ) = factory(componentContext, onBackParameter)
    }
}