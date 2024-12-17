package com.flipperdevices.bsb.auth.confirmpassword.model

import busystatusbar.components.bsb.auth.confirmpassword.impl.generated.resources.Res
import busystatusbar.components.bsb.auth.confirmpassword.impl.generated.resources.login_confirmpassword_hint

data class PasswordFieldsState(
    val passwordField: PasswordFieldState = PasswordFieldState(
        subText = Res.string.login_confirmpassword_hint
    ),
    val confirmField: PasswordFieldState = PasswordFieldState()
) {

    val confirmDisabled = passwordField.text.isBlank() || confirmField.text.isBlank() ||
        passwordField.validationError || confirmField.validationError
}
