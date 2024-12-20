package com.flipperdevices.bsb.auth.login.api

import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.bsb.deeplink.model.Deeplink
import com.flipperdevices.ui.decompose.CompositeDecomposeComponent
import com.flipperdevices.ui.decompose.DecomposeOnBackParameter

abstract class LoginDecomposeComponent<C : Any> : CompositeDecomposeComponent<C>() {
    abstract fun handleDeeplink(deeplink: Deeplink.Root.Auth.VerifyEmailLink.ResetPassword)

    fun interface Factory {
        @Suppress("LongParameterList")
        operator fun invoke(
            componentContext: ComponentContext,
            onBack: DecomposeOnBackParameter,
            email: String,
            preFilledPassword: String?,
            deeplink: Deeplink.Root.Auth.VerifyEmailLink.ResetPassword?,
            onComplete: () -> Unit,
        ): LoginDecomposeComponent<*>
    }
}
