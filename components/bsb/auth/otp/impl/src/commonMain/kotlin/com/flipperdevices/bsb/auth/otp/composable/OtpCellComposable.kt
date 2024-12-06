package com.flipperdevices.bsb.auth.otp.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flipperdevices.bsb.auth.common.composable.UiConstants
import com.flipperdevices.bsb.auth.otp.model.OtpCell
import com.flipperdevices.bsb.core.theme.LocalBusyBarFonts
import com.flipperdevices.bsb.core.theme.LocalPallet

@Composable
fun OtpCellComposable(
    modifier: Modifier,
    disabled: Boolean,
    value: OtpCell,
    focused: Boolean,
    onInput: (TextFieldValue) -> Unit
) {
    val focusRequester = remember { FocusRequester() }

    BasicTextField(
        modifier = modifier.graphicsLayer {
            if (disabled) {
                this.alpha = UiConstants.ALPHA_DISABLED
            }
        }.focusRequester(focusRequester),
        enabled = disabled.not(),
        value = value.textFieldValue,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Number
        ),
        onValueChange = { onInput(it) },
        maxLines = 1,
        textStyle = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.W500,
            fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
            color = LocalPallet.current.black.invert,
            textAlign = TextAlign.Center
        ),
        cursorBrush = SolidColor(LocalPallet.current.black.invert),
        decorationBox = { innerTextField ->
            OtpCellDecorationBox(
                innerTextField = innerTextField,
            )
        }
    )

    LaunchedEffect(focused) {
        if (focused) {
            focusRequester.requestFocus()
        }
    }
}

@Composable
private fun OtpCellDecorationBox(
    innerTextField: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .border(1.dp, LocalPallet.current.neutral.quinary, RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(LocalPallet.current.neutral.septenary)
            .padding(vertical = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        innerTextField()
    }
}