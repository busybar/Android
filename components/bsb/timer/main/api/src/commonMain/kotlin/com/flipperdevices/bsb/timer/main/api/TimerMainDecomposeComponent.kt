package com.flipperdevices.bsb.timer.main.api

import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.ui.decompose.CompositeDecomposeComponent

abstract class TimerMainDecomposeComponent<C : Any> : CompositeDecomposeComponent<C>() {
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext
        ): TimerMainDecomposeComponent<*>
    }
}