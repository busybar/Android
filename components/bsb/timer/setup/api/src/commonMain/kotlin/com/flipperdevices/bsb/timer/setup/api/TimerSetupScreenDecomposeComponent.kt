package com.flipperdevices.bsb.timer.setup.api

import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.core.data.timer.TimerState
import com.flipperdevices.ui.decompose.ElementDecomposeComponent
import kotlinx.coroutines.flow.StateFlow

abstract class TimerSetupScreenDecomposeComponent(
    componentContext: ComponentContext
) : ElementDecomposeComponent(componentContext) {
    abstract val timerState: StateFlow<TimerState>

    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
        ): TimerSetupScreenDecomposeComponent
    }
}