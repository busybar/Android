package com.flipperdevices.bsb.auth.otp.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.ui.decompose.ScreenDecomposeComponent

abstract class AuthOtpElementDecomposeComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {
    @Composable
    abstract fun Render(modifier: Modifier)

    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
        ): AuthOtpElementDecomposeComponent
    }
}