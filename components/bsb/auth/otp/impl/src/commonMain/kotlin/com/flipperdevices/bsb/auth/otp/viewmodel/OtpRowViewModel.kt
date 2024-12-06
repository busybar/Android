package com.flipperdevices.bsb.auth.otp.viewmodel

import androidx.compose.ui.text.input.TextFieldValue
import com.flipperdevices.bsb.auth.otp.model.OtpRow
import com.flipperdevices.core.ui.lifecycle.DecomposeViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import me.tatarka.inject.annotations.Inject

@Inject
class OtpRowViewModel : DecomposeViewModel() {
    private val state = MutableStateFlow(OtpRow())

    fun getState() = state.asStateFlow()

    fun onChange(index: Int, newLine: TextFieldValue) {
        state.update {
            it.onChange(index, newLine)
        }
    }
}