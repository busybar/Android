package com.flipperdevices.bsb.auth.otp.model

sealed interface OtpCellAction {
    data object MoveBracketLeft : OtpCellAction
    data class MoveBracketRight(val remainingText: String?) : OtpCellAction
}