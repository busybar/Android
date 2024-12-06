package com.flipperdevices.bsb.auth.otp.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.flipperdevices.bsb.auth.otp.model.OtpRow

@Composable
fun OtpRowComposable(
    modifier: Modifier,
    otpRow: OtpRow,
    onInput: (index: Int, newInput: TextFieldValue) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        otpRow.cells.forEachIndexed { index, char ->
            OtpCellComposable(
                modifier = Modifier.weight(1f),
                disabled = false,
                value = char,
                onInput = { onInput(index, it) },
                focused = index == otpRow.currentFocusIndex
            )
        }
    }
}