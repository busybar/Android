package com.flipperdevices.bsb.auth.confirmpassword.model

sealed interface ConfirmPasswordType {
    val email: String

    data class ResetPassword(
        override val email: String
    ) : ConfirmPasswordType

    data class SignUpPassword(
        override val email: String
    ) : ConfirmPasswordType
}