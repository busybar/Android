package com.flipperdevices.busybar.login.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.login_email_hint
import busystatusbar.composeapp.generated.resources.login_password_hint
import com.flipperdevices.busybar.core.theme.BusyBarThemeInternal
import com.flipperdevices.busybar.core.theme.LocalBusyBarFonts
import com.flipperdevices.busybar.core.theme.LocalPallet
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun EmailFieldComposable(modifier: Modifier) {
    EditTextComposable(
        modifier = modifier,
        hint = Res.string.login_email_hint,
        keyboardType = KeyboardType.Email
    )
}

@Composable
fun PasswordFieldComposable(modifier: Modifier) {
    EditTextComposable(
        modifier = modifier,
        hint = Res.string.login_password_hint,
        keyboardType = KeyboardType.Password,
        visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
private fun EditTextComposable(
    modifier: Modifier,
    hint: StringResource,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    var text by remember { mutableStateOf("") }
    val decorationBox = @Composable { innerTextField: @Composable () -> Unit ->
        Box(
            modifier = Modifier.clip(RoundedCornerShape(8.dp))
                .border(
                    1.dp,
                    LocalPallet.current.transparent.black.secondary,
                    RoundedCornerShape(8.dp)
                )
                .background(LocalPallet.current.transparent.black.quaternary)
                .padding(
                    vertical = 16.dp,
                    horizontal = 12.dp
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            innerTextField()
            if (text.isEmpty()) {
                Text(
                    text = stringResource(hint),
                    fontSize = 16.sp,
                    lineHeight = 19.2.sp,
                    fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
                    fontWeight = FontWeight.W400,
                    letterSpacing = 0.48.sp,
                    color = LocalPallet.current.neutral.secondary
                )
            }
        }
    }
    BasicTextField(
        modifier = modifier.height(51.dp)
            .fillMaxWidth(),
        value = text,
        onValueChange = { text = it },
        decorationBox = decorationBox,
        textStyle = LocalTextStyle.current.copy(
            fontSize = 16.sp,
            fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
            fontWeight = FontWeight.W400,
            letterSpacing = 0.48.sp,
            color = LocalPallet.current.invert.black,
            lineHeight = 19.2.sp
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = true,
        cursorBrush = SolidColor(LocalPallet.current.invert.black),
        visualTransformation = visualTransformation
    )
}

@Preview
@Composable
private fun EditTextLoginFieldsComposablePreview() {
    BusyBarThemeInternal {
        Column {
            EmailFieldComposable(Modifier)
        }
    }
}