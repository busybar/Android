package com.flipperdevices.bsb.auth.otp.element.model

sealed interface OtpCellAction {
    data object MoveBracketLeft : OtpCellAction
    data class MoveBracketRight(val remainingText: String?) : OtpCellAction
}
