package com.flipperdevices.bsb.auth.login.model

import com.flipperdevices.bsb.deeplink.model.Deeplink
import kotlinx.serialization.Serializable

@Serializable
sealed interface LoginNavigationConfig {
    @Serializable
    data object Password : LoginNavigationConfig

    @Serializable
    data class ResetPassword(
        val deeplink: Deeplink.Root.Auth.VerifyEmailLink.ResetPassword?
    ) : LoginNavigationConfig

    @Serializable
    data class ResetConfirmPassword(val code: String) : LoginNavigationConfig
}
