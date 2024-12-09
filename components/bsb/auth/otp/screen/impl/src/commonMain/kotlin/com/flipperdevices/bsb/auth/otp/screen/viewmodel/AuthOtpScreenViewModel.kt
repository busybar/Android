package com.flipperdevices.bsb.auth.otp.screen.viewmodel

import com.flipperdevices.bsb.auth.otp.screen.model.AuthOtpScreenState
import com.flipperdevices.bsb.auth.otp.screen.model.InternalAuthOtpType
import com.flipperdevices.core.ui.lifecycle.DecomposeViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class AuthOtpScreenViewModel(
    @Assisted otpType: InternalAuthOtpType
) : DecomposeViewModel() {
    private val state = MutableStateFlow<AuthOtpScreenState>(AuthOtpScreenState.WaitingForInput)

    fun getState() = state.asStateFlow()

}