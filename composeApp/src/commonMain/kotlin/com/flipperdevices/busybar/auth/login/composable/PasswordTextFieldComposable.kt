package com.flipperdevices.busybar.auth.login.composable

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.ic_hidden
import busystatusbar.composeapp.generated.resources.ic_lock
import busystatusbar.composeapp.generated.resources.ic_visible
import busystatusbar.composeapp.generated.resources.login_signin_password_hint
import com.flipperdevices.busybar.auth.common.composable.textfield.AuthCommonTextFieldComposable
import com.flipperdevices.busybar.core.ktx.clickableRipple
import com.flipperdevices.busybar.core.theme.LocalPallet
import org.jetbrains.compose.resources.painterResource

@Composable
fun PasswordTextFieldComposable(
    modifier: Modifier,
    password: String,
    onPasswordChange: (String) -> Unit,
    disabled: Boolean
) {
    var passwordHidden by remember { mutableStateOf(true) }

    AuthCommonTextFieldComposable(
        modifier = modifier,
        text = password,
        onTextChange = onPasswordChange,
        hint = Res.string.login_signin_password_hint,
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
        disabled = disabled
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