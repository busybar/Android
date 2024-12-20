package com.flipperdevices.bsb.auth.common.composable.textfield

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import busystatusbar.components.bsb.auth.common.generated.resources.Res
import busystatusbar.components.bsb.auth.common.generated.resources.ic_hidden
import busystatusbar.components.bsb.auth.common.generated.resources.ic_lock
import busystatusbar.components.bsb.auth.common.generated.resources.ic_visible
import com.flipperdevices.bsb.auth.common.composable.utils.autofill
import com.flipperdevices.bsb.core.theme.LocalPallet
import com.flipperdevices.core.ktx.common.clickableRipple
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PasswordTextFieldComposable(
    password: String,
    onPasswordChange: (String) -> Unit,
    disabled: Boolean,
    hint: StringResource,
    isNewPassword: Boolean,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    onAutofill: (String) -> Unit = onPasswordChange,
) {
    var passwordHidden by remember { mutableStateOf(true) }

    AuthCommonTextFieldComposable(
        modifier = modifier
            .autofill(
                if (isNewPassword) {
                    AutofillType.NewPassword
                } else {
                    AutofillType.Password
                },
                onFill = onAutofill
            ),
        text = password,
        onTextChange = onPasswordChange,
        hint = hint,
        icon = Res.drawable.ic_lock,
        endBlock = {
            HideIcon(passwordHidden, onClick = {
                passwordHidden = passwordHidden.not()
            })
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        visualTransformation = if (passwordHidden) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        disabled = disabled,
        isError = isError
    )
}

@Composable
private fun HideIcon(
    isHidden: Boolean,
    onClick: () -> Unit
) {
    Icon(
        modifier = Modifier.clickableRipple(onClick = onClick, bounded = false),
        painter = painterResource(
            if (isHidden) {
                Res.drawable.ic_hidden
            } else {
                Res.drawable.ic_visible
            }
        ),
        contentDescription = null,
        tint = LocalPallet.current.neutral.tertiary
    )
}
