package com.flipperdevices.bsb.preferencescreen.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.core.di.AppGraph
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@Inject
class PreferenceScreenScreenDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
) : PreferenceScreenScreenDecomposeComponent(componentContext) {
    @Composable
    override fun Render(modifier: Modifier) {

    }

    @Inject
    @ContributesBinding(AppGraph::class, PreferenceScreenScreenDecomposeComponent.Factory::class)
    class Factory(
        private val factory: (
            componentContext: ComponentContext
        ) -> PreferenceScreenScreenDecomposeComponentImpl
    ) : PreferenceScreenScreenDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext
        ) = factory(componentContext)
    }
}