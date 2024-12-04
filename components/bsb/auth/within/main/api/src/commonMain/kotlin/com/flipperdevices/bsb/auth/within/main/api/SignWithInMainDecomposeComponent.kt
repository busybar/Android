package com.flipperdevices.bsb.auth.within.main.api

import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.bsb.auth.within.main.model.SignWithInStateListener
import com.flipperdevices.ui.decompose.ElementDecomposeComponent

abstract class SignWithInMainDecomposeComponent(
    componentContext: ComponentContext
) : SignWithInElementDecomposeComponent(componentContext) {
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            withInStateListener: SignWithInStateListener
        ): SignWithInMainDecomposeComponent
    }
}