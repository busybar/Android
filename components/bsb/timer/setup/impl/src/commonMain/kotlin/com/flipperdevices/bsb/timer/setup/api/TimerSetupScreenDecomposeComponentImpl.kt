package com.flipperdevices.bsb.timer.setup.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.bsb.timer.setup.composable.TimerSetupComposable
import com.flipperdevices.core.di.AppGraph
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@Inject
class TimerSetupScreenDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
) : TimerSetupScreenDecomposeComponent(componentContext) {
    @Composable
    override fun Render(modifier: Modifier) {
        TimerSetupComposable(modifier)
    }

    @Inject
    @ContributesBinding(AppGraph::class, TimerSetupScreenDecomposeComponent.Factory::class)
    class Factory(
        private val factory: (
            componentContext: ComponentContext
        ) -> TimerSetupScreenDecomposeComponentImpl
    ) : TimerSetupScreenDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext
        ) = factory(componentContext)
    }
}