package com.flipperdevices.bsb.auth.signup.model

import com.flipperdevices.bsb.deeplink.model.Deeplink
import kotlinx.serialization.Serializable

@Serializable
sealed interface SignupNavigationConfig {
    @Serializable
    data object Main : SignupNavigationConfig

    @Serializable
    data class ConfirmEmail(val deeplink: Deeplink.Root.Auth.VerifyEmailLink.SignUp?) : SignupNavigationConfig

    @Serializable
    data class EnterPassword(
        val code: String
    ) : SignupNavigationConfig
}
