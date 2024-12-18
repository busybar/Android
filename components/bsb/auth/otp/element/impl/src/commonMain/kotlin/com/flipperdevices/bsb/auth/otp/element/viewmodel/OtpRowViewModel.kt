package com.flipperdevices.bsb.auth.otp.element.viewmodel

import androidx.compose.ui.text.input.TextFieldValue
import com.flipperdevices.bsb.auth.otp.element.model.OtpRow
import com.flipperdevices.core.ui.lifecycle.DecomposeViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import me.tatarka.inject.annotations.Inject

@Inject
class OtpRowViewModel(
    preFilledCode: String?
) : DecomposeViewModel() {
    private val state = MutableStateFlow(OtpRow(preFilledCode ?: ""))

    fun getState() = state.asStateFlow()

    fun insertOtp(row: String) {
        state.update {
            OtpRow(row)
        }
    }

    fun onChange(index: Int, newLine: TextFieldValue) {
        state.update {
            it.onChange(index, newLine)
        }
    }

    fun onFocus() {
        state.update {
            it.copy(currentFocusIndex = 0)
        }
    }
}
