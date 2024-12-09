package com.flipperdevices.bsb.auth.otp.element.model

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.flipperdevices.bsb.auth.otp.element.model.OtpCell
import com.flipperdevices.bsb.auth.otp.element.model.OtpCellAction
import kotlin.test.Test
import kotlin.test.assertEquals

class OtpCellTest {
    @Test
    fun newValueCharacter() {
        val currentState = TextFieldValue(
            text = "\u200E",
            selection = TextRange(1)
        )

        val newState = TextFieldValue(
            text = "\u200E1",
            selection = TextRange(2)
        )

        val (cell, action) = OtpCell(currentState).onApply(newState)

        assertEquals(
            TextFieldValue(
                text = "\u200E1",
                selection = TextRange(2)
            ), cell.textFieldValue
        )
        assertEquals(action, OtpCellAction.MoveBracketRight(null))
    }

    @Test
    fun newValueCharacterBeforeInvisible() {
        val currentState = TextFieldValue(
            text = "\u200E",
            selection = TextRange(1)
        )

        val newState = TextFieldValue(
            text = "1\u200E",
            selection = TextRange(1)
        )

        val (cell, action) = OtpCell(currentState).onApply(newState)

        assertEquals(
            TextFieldValue(
                text = "\u200E1",
                selection = TextRange(2)
            ), cell.textFieldValue
        )
        assertEquals(action, OtpCellAction.MoveBracketRight(null))
    }

    @Test
    fun newManyValueCharacter() {
        val currentState = TextFieldValue(
            text = "\u200E",
            selection = TextRange(1)
        )

        val newState = TextFieldValue(
            text = "\u200E123",
            selection = TextRange(4)
        )

        val (cell, action) = OtpCell(currentState).onApply(newState)

        assertEquals(
            TextFieldValue(
                text = "\u200E1",
                selection = TextRange(2)
            ), cell.textFieldValue
        )
        assertEquals(action, OtpCellAction.MoveBracketRight("23"))
    }

    @Test
    fun newManyValueCharacterBeforeInvisible() {
        val currentState = TextFieldValue(
            text = "\u200E",
            selection = TextRange(1)
        )

        val newState = TextFieldValue(
            text = "123\u200E",
            selection = TextRange(3)
        )

        val (cell, action) = OtpCell(currentState).onApply(newState)

        assertEquals(
            TextFieldValue(
                text = "\u200E1",
                selection = TextRange(2)
            ), cell.textFieldValue
        )
        assertEquals(action, OtpCellAction.MoveBracketRight("23"))
    }

    @Test
    fun newLargeCharacter() {
        val currentState = TextFieldValue(
            text = "\u200E1",
            selection = TextRange(1)
        )

        val newState = TextFieldValue(
            text = "\u200E12",
            selection = TextRange(3)
        )

        val (cell, action) = OtpCell(currentState).onApply(newState)

        assertEquals(
            TextFieldValue(
                text = "\u200E1",
                selection = TextRange(2)
            ), cell.textFieldValue
        )
        assertEquals(action, OtpCellAction.MoveBracketRight("2"))
    }

    ////

    @Test
    fun removeCharacter() {
        val currentState = TextFieldValue(
            text = "\u200E1",
            selection = TextRange(2)
        )

        val newState = TextFieldValue(
            text = "\u200E",
            selection = TextRange(1)
        )

        val (cell, action) = OtpCell(currentState).onApply(newState)

        assertEquals(
            TextFieldValue(
                text = "\u200E",
                selection = TextRange(1)
            ), cell.textFieldValue
        )
        assertEquals(action, null)
    }

    @Test
    fun removeInvisibleBeforeValueCharacter() {
        val currentState = TextFieldValue(
            text = "\u200E1",
            selection = TextRange(1)
        )

        val newState = TextFieldValue(
            text = "1",
            selection = TextRange(0)
        )

        val (cell, action) = OtpCell(currentState).onApply(newState)

        assertEquals(
            TextFieldValue(
                text = "\u200E1",
                selection = TextRange(1)
            ), cell.textFieldValue
        )
        assertEquals(action, OtpCellAction.MoveBracketLeft)
    }

    @Test
    fun removeInvisibleCharacter() {
        val currentState = TextFieldValue(
            text = "\u200E",
            selection = TextRange(1)
        )

        val newState = TextFieldValue(
            text = "",
            selection = TextRange(0)
        )

        val (cell, action) = OtpCell(currentState).onApply(newState)

        assertEquals(
            TextFieldValue(
                text = "\u200E",
                selection = TextRange(1)
            ), cell.textFieldValue
        )
        assertEquals(action, OtpCellAction.MoveBracketLeft)
    }


    @Test
    fun removeWholeLite() {
        val currentState = TextFieldValue(
            text = "\u200E1",
            selection = TextRange(1)
        )

        val newState = TextFieldValue(
            text = "",
            selection = TextRange(0)
        )

        val (cell, action) = OtpCell(currentState).onApply(newState)

        assertEquals(
            TextFieldValue(
                text = "\u200E",
                selection = TextRange(1)
            ), cell.textFieldValue
        )
        assertEquals(action, OtpCellAction.MoveBracketLeft)
    }


    @Test
    fun changeBracket() {
        val currentState = TextFieldValue(
            text = "\u200E1",
            selection = TextRange(1)
        )

        val newState = TextFieldValue(
            text = "\u200E1",
            selection = TextRange(2)
        )

        val (cell, action) = OtpCell(currentState).onApply(newState)

        assertEquals(
            TextFieldValue(
                text = "\u200E1",
                selection = TextRange(2)
            ), cell.textFieldValue
        )
        assertEquals(action, null)
    }
}