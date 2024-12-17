package com.flipperdevices.bsb.auth.otp.element.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.bsb.auth.otp.element.model.OtpElementState
import com.flipperdevices.ui.decompose.ScreenDecomposeComponent
import kotlinx.coroutines.flow.StateFlow

abstract class AuthOtpElementDecomposeComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {
    @Composable
    abstract fun Render(
        modifier: Modifier,
        otpElementState: OtpElementState,
        onFocus: () -> Unit
    )

    abstract suspend fun onFocus()

    abstract fun getOtpCodeState(): StateFlow<String>

    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
        ): AuthOtpElementDecomposeComponent
    }
}