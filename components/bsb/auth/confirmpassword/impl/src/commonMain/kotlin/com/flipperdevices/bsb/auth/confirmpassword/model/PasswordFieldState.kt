package com.flipperdevices.bsb.auth.confirmpassword.model

import org.jetbrains.compose.resources.StringResource

data class PasswordFieldState(
    val text: String = "",
    val validationError: Boolean = false,
    val subText: StringResource? = null
)
