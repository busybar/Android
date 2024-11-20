package com.flipperdevices.bsb.timer.setup.api

import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.ui.decompose.ScreenDecomposeComponent

abstract class TimerSetupScreenDecomposeComponent(
    componentContext: ComponentContext
) : ScreenDecomposeComponent(componentContext) {
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
        ): TimerSetupScreenDecomposeComponent
    }
}