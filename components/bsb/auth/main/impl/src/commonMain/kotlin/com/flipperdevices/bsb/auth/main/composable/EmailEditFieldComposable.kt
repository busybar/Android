package com.flipperdevices.bsb.auth.main.composable

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import busystatusbar.components.bsb.auth.main.impl.generated.resources.Res
import busystatusbar.components.bsb.auth.main.impl.generated.resources.ic_user
import busystatusbar.components.bsb.auth.main.impl.generated.resources.login_main_email_hint
import com.flipperdevices.bsb.auth.common.composable.textfield.AuthCommonTextFieldComposable

@Composable
fun EmailEditFieldComposable(
    modifier: Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    disabled: Boolean
) {
    AuthCommonTextFieldComposable(
        modifier = modifier,
        icon = Res.drawable.ic_user,
        text = text,
        onTextChange = onTextChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        disabled = disabled,
        hint = Res.string.login_main_email_hint
    )
}