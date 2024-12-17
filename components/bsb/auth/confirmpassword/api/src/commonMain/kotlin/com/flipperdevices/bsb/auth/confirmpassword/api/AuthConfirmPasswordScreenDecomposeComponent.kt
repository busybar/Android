package com.flipperdevices.bsb.auth.confirmpassword.api

import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.bsb.auth.confirmpassword.model.ConfirmPasswordType
import com.flipperdevices.ui.decompose.DecomposeOnBackParameter
import com.flipperdevices.ui.decompose.ScreenDecomposeComponent

abstract class AuthConfirmPasswordScreenDecomposeComponent(
    componentContext: ComponentContext
) : ScreenDecomposeComponent(componentContext) {
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            type: ConfirmPasswordType,
            onBackParameter: DecomposeOnBackParameter,
            onComplete: () -> Unit,
        ): AuthConfirmPasswordScreenDecomposeComponent
    }
}
