package com.flipperdevices.bsb.cloud.model

fun BSBEmailVerificationType.toVerificationTypeString() = when (this) {
    BSBEmailVerificationType.SIGN_UP -> "sign-up-account"
    BSBEmailVerificationType.RESET_PASSWORD -> "reset-password"
}
