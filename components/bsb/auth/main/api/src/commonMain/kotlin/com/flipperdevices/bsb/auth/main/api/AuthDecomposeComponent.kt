package com.flipperdevices.bsb.auth.main.api

import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.bsb.deeplink.model.Deeplink
import com.flipperdevices.ui.decompose.CompositeDecomposeComponent
import com.flipperdevices.ui.decompose.DecomposeOnBackParameter

abstract class AuthDecomposeComponent<T : Any> : CompositeDecomposeComponent<T>() {
    abstract fun handleDeeplink(deeplink: Deeplink.Root.Auth)

    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            onBackParameter: DecomposeOnBackParameter,
            deeplink: Deeplink.Root.Auth?
        ): AuthDecomposeComponent<*>
    }
}
