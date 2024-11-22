package com.flipperdevices.bsb.timer.setup.model

import kotlinx.serialization.Serializable

@Serializable
data class TimerState(
    val minute: Int,
    val second: Int
)