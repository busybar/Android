package com.flipperdevices.bsb.auth.otp.screen.model

import com.flipperdevices.core.data.timer.TimerState

sealed interface AuthOtpExpiryState {
    data class Ready(val timerState: TimerState) : AuthOtpExpiryState

    data object Empty : AuthOtpExpiryState

    data object Error : AuthOtpExpiryState
}