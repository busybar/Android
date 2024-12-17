package com.flipperdevices.bsb.auth.confirmpassword.model

sealed interface ConfirmPasswordType {
    val email: String
    val otpCode: String
    val preFilledPassword: String?

    data class ResetPassword(
        override val email: String,
        override val otpCode: String,
        override val preFilledPassword: String?
    ) : ConfirmPasswordType

    data class SignUpPassword(
        override val email: String,
        override val otpCode: String,
        override val preFilledPassword: String?
    ) : ConfirmPasswordType
}