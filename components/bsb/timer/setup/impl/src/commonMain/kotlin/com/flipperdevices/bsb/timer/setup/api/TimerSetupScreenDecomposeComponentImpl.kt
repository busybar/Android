package com.flipperdevices.bsb.timer.setup.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.bsb.timer.background.model.TimerState
import com.flipperdevices.bsb.timer.setup.composable.DEFAULT_MINUTE
import com.flipperdevices.bsb.timer.setup.composable.DEFAULT_SECOND
import com.flipperdevices.bsb.timer.setup.composable.TimerSetupComposable
import com.flipperdevices.core.di.AppGraph
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@Inject
class TimerSetupScreenDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
) : TimerSetupScreenDecomposeComponent(componentContext) {
    override val timerState = MutableStateFlow(
        TimerState(
            minute = DEFAULT_MINUTE,
            second = DEFAULT_SECOND
        )
    )


    @Composable
    override fun Render(modifier: Modifier) {
        TimerSetupComposable(
            modifier = modifier,
            onChangeTimer = {
                timerState.emit(it)
            }
        )
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