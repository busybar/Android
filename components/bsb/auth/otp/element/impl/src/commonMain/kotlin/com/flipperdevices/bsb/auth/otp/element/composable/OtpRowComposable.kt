package com.flipperdevices.bsb.auth.otp.element.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.flipperdevices.bsb.auth.common.composable.UiConstants.ALPHA_DISABLED
import com.flipperdevices.bsb.auth.otp.element.model.OtpElementState
import com.flipperdevices.bsb.auth.otp.element.model.OtpRow
import com.flipperdevices.bsb.core.theme.LocalPallet

@Composable
fun OtpRowComposable(
    modifier: Modifier,
    otpRow: OtpRow,
    otpElementState: OtpElementState,
    onInput: (index: Int, newInput: TextFieldValue) -> Unit
) {
    val inProgress = when (otpElementState) {
        OtpElementState.WAITING_FOR_INPUT,
        OtpElementState.ERROR -> false

        OtpElementState.IN_PROGRESS -> true
    }

    Row(
        modifier = modifier.graphicsLayer {
            if (inProgress) {
                this.alpha = ALPHA_DISABLED
            }
        },
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        otpRow.cells.forEachIndexed { index, char ->
            OtpCellComposable(
                modifier = Modifier.weight(1f),
                inProgress = inProgress,
                value = char,
                onInput = { onInput(index, it) },
                focused = index == otpRow.currentFocusIndex,
                borderColor = when (otpElementState) {
                    OtpElementState.WAITING_FOR_INPUT,
                    OtpElementState.IN_PROGRESS -> LocalPallet.current.neutral.quinary

                    OtpElementState.ERROR -> LocalPallet.current.danger.secondary
                },
                backgroundColor = when (otpElementState) {
                    OtpElementState.WAITING_FOR_INPUT,
                    OtpElementState.IN_PROGRESS -> LocalPallet.current.neutral.septenary

                    OtpElementState.ERROR -> LocalPallet.current.danger.tertiary
                }
            )
        }
    }
}