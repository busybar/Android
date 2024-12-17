package com.flipperdevices.bsb.auth.within.main.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.bsb.auth.within.main.model.SignWithInState

abstract class SignWithInElementDecomposeComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {
    @Composable
    abstract fun Render(
        modifier: Modifier,
        authState: SignWithInState
    )
}
