package com.flipperdevices.bsb.timer.setup.api

import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.bsb.timer.setup.model.TimerState
import com.flipperdevices.ui.decompose.ScreenDecomposeComponent
import kotlinx.coroutines.flow.StateFlow

abstract class TimerSetupScreenDecomposeComponent(
    componentContext: ComponentContext
) : ScreenDecomposeComponent(componentContext) {
    abstract val timerState: StateFlow<TimerState>

    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
        ): TimerSetupScreenDecomposeComponent
    }
}