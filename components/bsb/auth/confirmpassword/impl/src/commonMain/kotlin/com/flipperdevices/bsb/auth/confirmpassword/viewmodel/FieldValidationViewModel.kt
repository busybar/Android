package com.flipperdevices.bsb.auth.confirmpassword.viewmodel

import busystatusbar.components.bsb.auth.confirmpassword.impl.generated.resources.Res
import busystatusbar.components.bsb.auth.confirmpassword.impl.generated.resources.login_confirmpassword_confirm_hint_error
import busystatusbar.components.bsb.auth.confirmpassword.impl.generated.resources.login_confirmpassword_hint
import busystatusbar.components.bsb.auth.confirmpassword.impl.generated.resources.login_confirmpassword_hint_error_lowercase
import busystatusbar.components.bsb.auth.confirmpassword.impl.generated.resources.login_confirmpassword_hint_error_min
import busystatusbar.components.bsb.auth.confirmpassword.impl.generated.resources.login_confirmpassword_hint_error_special_char
import busystatusbar.components.bsb.auth.confirmpassword.impl.generated.resources.login_confirmpassword_hint_error_uppercase
import com.flipperdevices.bsb.auth.confirmpassword.model.PasswordFieldState
import com.flipperdevices.bsb.auth.confirmpassword.model.PasswordFieldsState
import com.flipperdevices.core.ui.lifecycle.DecomposeViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import org.jetbrains.compose.resources.StringResource

private const val PASSWORD_LENGTH = 8
private val PASSWORD_REGEX_LOWERCASE = "[a-z]".toRegex()
private val PASSWORD_REGEX_UPPERCASE = "[A-Z]".toRegex()
private val PASSWORD_REGEX_SPECIAL = "[^a-zA-Z0-9]".toRegex()

@Inject
class FieldValidationViewModel(
    @Assisted preFilledPassword: String?
) : DecomposeViewModel() {
    private val fieldState = MutableStateFlow(PasswordFieldsState())

    init {
        if (!preFilledPassword.isNullOrBlank()) {
            onPasswordFieldChange(preFilledPassword)
            onConfirmFieldChange(preFilledPassword)
        }
    }

    fun getState() = fieldState.asStateFlow()

    fun onPasswordFieldChange(text: String) {
        val validatePasswordText = validatePassword(text)

        fieldState.update {
            it.copy(
                passwordField = PasswordFieldState(
                    text = text.trim(),
                    validationError = validatePasswordText != null,
                    subText = validatePasswordText ?: Res.string.login_confirmpassword_hint
                )
            )
        }
    }

    fun onConfirmFieldChange(text: String) {
        fieldState.update { fields ->
            fields.copy(
                confirmField = PasswordFieldState(
                    text = text,
                    validationError = text != fields.passwordField.text,
                    subText = if (text.trim() == fields.passwordField.text.trim()) {
                        null
                    } else {
                        Res.string.login_confirmpassword_confirm_hint_error
                    }
                )
            )
        }
    }
}

private fun validatePassword(password: String): StringResource? {
    if (password.length < PASSWORD_LENGTH) {
        return Res.string.login_confirmpassword_hint_error_min
    }

    if (password.contains(PASSWORD_REGEX_LOWERCASE).not()) {
        return Res.string.login_confirmpassword_hint_error_lowercase
    }

    if (password.contains(PASSWORD_REGEX_UPPERCASE).not()) {
        return Res.string.login_confirmpassword_hint_error_uppercase
    }

    if (password.contains(PASSWORD_REGEX_SPECIAL).not()) {
        return Res.string.login_confirmpassword_hint_error_special_char
    }

    return null
}
