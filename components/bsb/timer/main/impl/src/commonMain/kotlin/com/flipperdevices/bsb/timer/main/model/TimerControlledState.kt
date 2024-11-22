package com.flipperdevices.bsb.timer.main.model

import com.flipperdevices.bsb.timer.setup.model.TimerState
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class TimerControlledState(
    val timerState: TimerState,
    val pauseOn: Instant? = null
)