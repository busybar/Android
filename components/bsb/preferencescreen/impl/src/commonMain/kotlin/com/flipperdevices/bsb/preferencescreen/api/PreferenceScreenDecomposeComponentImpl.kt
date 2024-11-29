package com.flipperdevices.bsb.preferencescreen.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.bsb.dnd.api.BusyDNDApi
import com.flipperdevices.bsb.preference.api.ThemeStatusBarIconStyleProvider
import com.flipperdevices.bsb.preferencescreen.composable.PreferenceScreenComposable
import com.flipperdevices.core.di.AppGraph
import com.flipperdevices.ui.decompose.statusbar.StatusBarIconStyleProvider
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@Inject
class PreferenceScreenDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
    private val statusBarIconStyleProvider: ThemeStatusBarIconStyleProvider,
    private val dndApi: BusyDNDApi
) : PreferenceScreenDecomposeComponent(componentContext),
    StatusBarIconStyleProvider by statusBarIconStyleProvider {
    @Composable
    override fun Render(modifier: Modifier) {
        PreferenceScreenComposable(
            modifier,
            onRequestDND = { dndApi.tryToEnable() }
        )
    }

    @Inject
    @ContributesBinding(AppGraph::class, PreferenceScreenDecomposeComponent.Factory::class)
    class Factory(
        private val factory: (
            componentContext: ComponentContext
        ) -> PreferenceScreenDecomposeComponentImpl
    ) : PreferenceScreenDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext
        ) = factory(componentContext)
    }
}