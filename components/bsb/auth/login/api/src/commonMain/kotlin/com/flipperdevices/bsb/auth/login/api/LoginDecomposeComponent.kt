package com.flipperdevices.bsb.auth.login.api

import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.bsb.deeplink.model.Deeplink
import com.flipperdevices.ui.decompose.CompositeDecomposeComponent
import com.flipperdevices.ui.decompose.DecomposeOnBackParameter

abstract class LoginDecomposeComponent<C : Any> : CompositeDecomposeComponent<C>() {
    abstract fun handleDeeplink(deeplink: Deeplink.Root.Auth.VerifyEmailLink.ResetPassword)

    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            onBack: DecomposeOnBackParameter,
            email: String,
            preFilledPassword: String?,
            onComplete: () -> Unit,
            deeplink: Deeplink.Root.Auth.VerifyEmailLink.ResetPassword?
        ): LoginDecomposeComponent<*>
    }
}
