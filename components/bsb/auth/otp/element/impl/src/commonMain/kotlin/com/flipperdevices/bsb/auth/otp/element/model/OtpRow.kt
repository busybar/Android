package com.flipperdevices.bsb.auth.otp.element.model

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

private const val OTP_LENGTH = 6

data class OtpRow(
    val cells: PersistentList<OtpCell> = Array(OTP_LENGTH) { OtpCell() }.toPersistentList(),
    val currentFocusIndex: Int? = 0
) {
    fun onChange(index: Int, newState: TextFieldValue): OtpRow {
        if (index >= cells.size) {
            return this
        }

        val newCells = cells.toMutableList()
        var currentIndex = index
        var nextState: TextFieldValue? = newState
        while (currentIndex < cells.size && nextState != null) {
            val (newCell, action) = newCells[index].onApply(nextState)
            newCells[currentIndex] = newCell
            when (action) {
                OtpCellAction.MoveBracketLeft -> {
                    if (currentIndex != 0) {
                        currentIndex--
                        nextState = TextFieldValue(
                            text = "$INVISIBLE_SYMBOL",
                            selection = TextRange(1)
                        )
                    } else {
                        nextState = null
                    }
                }

                is OtpCellAction.MoveBracketRight -> {
                    currentIndex++
                    if (action.remainingText.isNullOrBlank()) {
                        nextState = null
                    } else {
                        nextState = TextFieldValue(
                            text = "$INVISIBLE_SYMBOL${action.remainingText}",
                            selection = TextRange(2)
                        )
                    }
                }

                null -> nextState = null
            }
        }

        return copy(
            cells = newCells.toPersistentList(),
            currentFocusIndex = if (currentIndex > cells.lastIndex) {
                cells.lastIndex
            } else currentIndex
        )
    }
}