package com.flipperdevices.bsb.timer.background.api

import com.flipperdevices.bsb.timer.background.api.delegates.add
import com.flipperdevices.core.data.timer.TimerState
import kotlin.test.Test
import kotlin.test.assertEquals

class TimerStateActionTest {
    companion object {
        val plusSamplesWithResult = listOf(
            TimerState(25, 0) to TimerState(25, 5),
            TimerState(25, 59) to TimerState(26, 4),
            TimerState(25, 58) to TimerState(26, 3),
            TimerState(25, 57) to TimerState(26, 2),
            TimerState(25, 56) to TimerState(26, 1),
            TimerState(25, 55) to TimerState(26, 0),
        )
        val minusSamplesWithResult = listOf(
            TimerState(25, 0) to TimerState(24, 55),
            TimerState(25, 1) to TimerState(24, 56),
            TimerState(25, 2) to TimerState(24, 57),
            TimerState(25, 3) to TimerState(24, 58),
            TimerState(25, 4) to TimerState(24, 59),
            TimerState(25, 5) to TimerState(25, 0),
        )
    }

    @Test
    fun plusFiveArithmeticTest() {
        for ((sample, result) in plusSamplesWithResult) {
            val actual = sample.add(TimerState(0, 5))
            assertEquals(
                result,
                actual,
                "${sample.minute}:${sample.second}+00:05 != ${actual.minute}:${actual.second}"
            )
        }
    }

    @Test
    fun minusFiveArithmeticTest() {
        for ((sample, result) in minusSamplesWithResult) {
            val actual = sample.add(TimerState(0, -5))
            assertEquals(
                result,
                actual,
                "${sample.minute}:${sample.second}-00:05 != ${actual.minute}:${actual.second}"
            )
        }
    }
}