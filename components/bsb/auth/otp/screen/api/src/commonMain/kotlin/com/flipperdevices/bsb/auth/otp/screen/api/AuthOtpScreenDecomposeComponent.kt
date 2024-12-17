package com.flipperdevices.bsb.auth.otp.screen.api

import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.bsb.auth.otp.screen.model.AuthOtpType
import com.flipperdevices.ui.decompose.DecomposeOnBackParameter
import com.flipperdevices.ui.decompose.ScreenDecomposeComponent

abstract class AuthOtpScreenDecomposeComponent(
    componentContext: ComponentContext
) : ScreenDecomposeComponent(componentContext) {
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            onBack: DecomposeOnBackParameter,
            otpType: AuthOtpType,
            onOtpComplete: suspend (String) -> Unit,
        ): AuthOtpScreenDecomposeComponent
    }
}
