package com.flipperdevices.bsb.auth.signup.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface SignupNavigationConfig {
    @Serializable
    data object Main : SignupNavigationConfig

    @Serializable
    data object ConfirmEmail : SignupNavigationConfig

    @Serializable
    data class EnterPassword(
        val code: String
    ) : SignupNavigationConfig
}
