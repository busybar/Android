package com.flipperdevices.bsb.auth.otp.element.composable

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flipperdevices.bsb.auth.otp.element.model.OtpCell
import com.flipperdevices.bsb.core.theme.LocalBusyBarFonts
import com.flipperdevices.bsb.core.theme.LocalPallet
import com.flipperdevices.core.ktx.common.placeholder

@Composable
fun OtpCellComposable(
    modifier: Modifier,
    inProgress: Boolean,
    value: OtpCell,
    focused: Boolean,
    borderColor: Color,
    backgroundColor: Color,
    onInput: (TextFieldValue) -> Unit
) {
    val focusRequester = remember { FocusRequester() }

    BasicTextField(
        modifier = modifier.focusRequester(focusRequester)
            .placeholder(
                inProgress,
                shape = RoundedCornerShape(8.dp)
            ),
        enabled = inProgress.not(),
        value = value.textFieldValue,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Decimal
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
                borderColor = borderColor,
                backgroundColor = backgroundColor
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
    borderColor: Color,
    backgroundColor: Color,
) {
    Box(
        modifier = Modifier
            .border(1.dp, borderColor, RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .padding(vertical = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        innerTextField()
    }
}