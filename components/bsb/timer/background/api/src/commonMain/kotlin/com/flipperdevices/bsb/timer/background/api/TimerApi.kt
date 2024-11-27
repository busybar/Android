package com.flipperdevices.bsb.timer.background.api

import com.flipperdevices.bsb.timer.background.model.ControlledTimerState
import com.flipperdevices.bsb.timer.background.model.TimerAction
import com.flipperdevices.bsb.timer.background.model.TimerState
import kotlinx.coroutines.flow.StateFlow

interface TimerApi {
    fun startTimer(initialTimerState: TimerState)

    fun getState(): StateFlow<ControlledTimerState?>

    fun onAction(action: TimerAction)

    fun stopTimer()
}