package com.flipperdevices.bsb.timer.background.model

import kotlinx.serialization.Serializable

@Serializable
data class ControlledTimerState (
    val timerState: TimerState,
    val isOnPause: Boolean
)