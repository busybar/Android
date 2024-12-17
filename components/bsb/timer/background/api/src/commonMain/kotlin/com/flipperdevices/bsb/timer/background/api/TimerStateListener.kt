package com.flipperdevices.bsb.timer.background.api

interface TimerStateListener {
    fun onTimerStart() = Unit
    fun onTimerStop() = Unit
}
