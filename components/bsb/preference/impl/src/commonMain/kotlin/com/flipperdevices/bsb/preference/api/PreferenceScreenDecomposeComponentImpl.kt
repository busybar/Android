package com.flipperdevices.bsb.preference.api

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.core.di.AppGraph
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@Inject
class PreferenceScreenDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
) : PreferenceScreenDecomposeComponent(componentContext) {
    @Composable
    override fun Render() {

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