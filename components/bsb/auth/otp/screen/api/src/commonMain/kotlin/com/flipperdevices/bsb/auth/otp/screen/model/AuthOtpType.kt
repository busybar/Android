package com.flipperdevices.bsb.auth.otp.screen.model

sealed interface AuthOtpType {
    val email: String

    data class ForgotPassword(
        override val email: String
    ) : AuthOtpType

    data class VerifyEmail(
        override val email: String
    ) : AuthOtpType
}
