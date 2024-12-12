package com.flipperdevices.bsb.auth.confirmpassword.model

import busystatusbar.components.bsb.auth.confirmpassword.impl.generated.resources.Res
import busystatusbar.components.bsb.auth.confirmpassword.impl.generated.resources.login_confirmpassword_hint

data class PasswordFieldsState(
    val passwordField: PasswordFieldState,
    val confirmField: PasswordFieldState,
    val confirmDisabled: Boolean
) {
    constructor(
        passwordField: PasswordFieldState = PasswordFieldState(
            subText = Res.string.login_confirmpassword_hint
        ),
        confirmField: PasswordFieldState = PasswordFieldState(),
    ) : this(
        passwordField, confirmField,
        confirmDisabled = passwordField.text.isBlank() || confirmField.text.isBlank()
                || passwordField.validationError || confirmField.validationError
    )
}