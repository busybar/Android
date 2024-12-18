package com.flipperdevices.bsb.auth.otp.element.model

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import kotlinx.collections.immutable.toPersistentList
import kotlin.test.Test
import kotlin.test.assertEquals

class OtpRowTest {
    @Test
    fun pasteInEmptyRow() {
        val emptyRow = OtpRow()

        val newRow = emptyRow.onChange(
            0,
            TextFieldValue(
                text = "\u200E123456",
                selection = TextRange(1)
            ),
        )

        val expected = OtpRow(
            cells = generateCells("123456"),
            currentFocusIndex = 5
        )

        assertEquals(
            expected = expected,
            actual = newRow,
            message = "Expected: ${expected.toLine()}, but actual is ${newRow.toLine()}"
        )
    }


    @Test
    fun pasteInFilledRow() {
        val emptyRow = OtpRow(
            cells = generateCells("789012"),
            currentFocusIndex = 0
        )

        val newRow = emptyRow.onChange(
            0,
            TextFieldValue(
                text = "\u200E1234567",
                selection = TextRange(1)
            ),
        )

        val expected = OtpRow(
            cells = generateCells("123456"),
            currentFocusIndex = 5
        )

        assertEquals(
            expected = expected,
            actual = newRow,
            message = "Expected: ${expected.toLine()}, but actual is ${newRow.toLine()}"
        )
    }


    @Test
    fun pasteInMiddle() {
        val emptyRow = OtpRow()

        val newRow = emptyRow.onChange(
            0,
            TextFieldValue(
                text = "\u200E123",
                selection = TextRange(1)
            ),
        ).onChange(
            3,
            TextFieldValue(
                text = "\u200E1234567",
                selection = TextRange(1)
            ),
        )

        val expected = OtpRow(
            cells = generateCells("123123"),
            currentFocusIndex = 5
        )

        assertEquals(
            expected = expected,
            actual = newRow,
            message = "Expected: ${expected.toLine()}, but actual is ${newRow.toLine()}"
        )
    }
}

private fun generateCells(row: String) = row.map { symbol ->
    OtpCell(
        TextFieldValue(
            text = "\u200E$symbol",
            selection = TextRange(1)
        )
    )
}.toPersistentList()

private fun OtpRow.toLine() = cells.map { it.textFieldValue.text }.joinToString("")