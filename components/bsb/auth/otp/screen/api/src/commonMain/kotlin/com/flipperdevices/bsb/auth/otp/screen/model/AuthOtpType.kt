package com.flipperdevices.bsb.auth.otp.screen.model

import kotlinx.datetime.Instant
import kotlin.time.Duration

sealed interface AuthOtpType {
    val email: String
    val codeExpiryTime: Instant

    data class ForgotPassword(
        override val email: String,
        override val codeExpiryTime: Instant
    ) : AuthOtpType

    data class VerifyEmail(
        override val email: String,
        override val codeExpiryTime: Instant
    ) : AuthOtpType
}