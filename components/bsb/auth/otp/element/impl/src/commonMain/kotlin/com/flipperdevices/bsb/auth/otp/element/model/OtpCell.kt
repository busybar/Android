package com.flipperdevices.bsb.auth.otp.element.model

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue


internal const val INVISIBLE_SYMBOL = '‎'

data class OtpCell(
    val textFieldValue: TextFieldValue = TextFieldValue(
        INVISIBLE_SYMBOL.toString(),
        selection = TextRange(1)
    )
) {
    fun onApply(newState: TextFieldValue): Pair<OtpCell, OtpCellAction?> {
        val newText = newState.text.filter { it.isDigit() || it == INVISIBLE_SYMBOL }

        if (textFieldValue.text == newState.text) {
            return copy(
                textFieldValue = newState.copy(
                    selection = TextRange(1)
                )
            ) to null
        }

        val invisibleCharacterExist = newText.firstOrNull() == INVISIBLE_SYMBOL
        if (!invisibleCharacterExist) {
            // "\u200E" -> ""
            // "\u200E1" -> ""
            return if (newText.isEmpty()) {
                copy(
                    textFieldValue = newState.copy(
                        text = "$INVISIBLE_SYMBOL",
                        selection = TextRange(1)
                    )
                ) to OtpCellAction.MoveBracketLeft
            } else if (newText.last() == INVISIBLE_SYMBOL) {
                // "\u200E" -> "1\u200E"
                // "\u200E" -> "123\u200E"
                val subText = newText.substring(0, newText.lastIndex)
                copy(
                    textFieldValue = newState.copy(
                        text = "$INVISIBLE_SYMBOL${subText.first()}",
                        selection = TextRange(1)
                    )
                ) to OtpCellAction.MoveBracketRight(
                    if (subText.length > 1) {
                        subText.substring(1)
                    } else null
                )
            } else { // "\u200E1" -> "1"
                copy(
                    textFieldValue = newState.copy(
                        text = "$INVISIBLE_SYMBOL${newText.first()}",
                        selection = TextRange(1)
                    )
                ) to OtpCellAction.MoveBracketLeft
            }
        }

        if (newText.length == 1) { // "\u200E1" -> "\u200E"
            return copy(
                textFieldValue = newState.copy(
                    text = "$INVISIBLE_SYMBOL",
                    selection = TextRange(1)
                )
            ) to null
        }

        // "\u200E" -> "\u200E1"
        // "\u200E" -> "\u200E123"
        // "\u200E1" -> "\u200E12"

        var subText = newText.substring(1)
        if (textFieldValue.selection.min == 1 && subText.length > 1) {
            subText = subText.removeRange(1, 2)
        }

        return copy(
            textFieldValue = newState.copy(
                text = "$INVISIBLE_SYMBOL${subText.first()}",
                selection = TextRange(1)
            )
        ) to OtpCellAction.MoveBracketRight(
            if (subText.length > 1) {
                subText.substring(1)
            } else null
        )
    }

}