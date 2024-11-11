package com.flipperdevices.busybar.auth.main.composable

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.ic_user
import com.flipperdevices.busybar.auth.common.composable.textfield.AuthCommonTextFieldComposable

@Composable
fun EmailEditFieldComposable(
    modifier: Modifier
) {
    var text by remember { mutableStateOf("") }

    AuthCommonTextFieldComposable(
        modifier = modifier,
        icon = Res.drawable.ic_user,
        text = text,
        onTextChange = { text = it },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        )
    )
}