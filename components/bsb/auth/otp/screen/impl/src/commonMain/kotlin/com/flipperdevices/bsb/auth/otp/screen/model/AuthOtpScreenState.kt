package com.flipperdevices.bsb.auth.otp.screen.model

sealed interface AuthOtpScreenState {
    data object WaitingForInput : AuthOtpScreenState

    data object CheckCodeInProgress : AuthOtpScreenState
}