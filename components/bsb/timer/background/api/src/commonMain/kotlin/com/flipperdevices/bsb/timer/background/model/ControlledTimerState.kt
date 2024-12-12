package com.flipperdevices.bsb.timer.background.model

import com.flipperdevices.core.data.timer.TimerState
import kotlinx.serialization.Serializable

@Serializable
data class ControlledTimerState (
    val timerState: TimerState,
    val isOnPause: Boolean
)