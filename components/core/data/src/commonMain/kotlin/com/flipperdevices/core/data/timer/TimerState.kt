package com.flipperdevices.core.data.timer

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

@Serializable
data class TimerState(
    val minute: Int,
    val second: Int
) {
    constructor(duration: Duration): this(
        minute = duration.inWholeMinutes.toInt(),
        second = (duration.inWholeSeconds - duration.inWholeMinutes * 1.minutes.inWholeSeconds).toInt()
    )


    fun toHumanReadableString(): String {
        var text = ""
        if (minute < 10) {
            text += "0"
        }
        text += "$minute:"

        if (second < 10) {
            text += "0"
        }
        text += second
        return text
    }
}