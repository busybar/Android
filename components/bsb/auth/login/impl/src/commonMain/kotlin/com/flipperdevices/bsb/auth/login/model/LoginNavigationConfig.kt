package com.flipperdevices.bsb.auth.login.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
sealed interface LoginNavigationConfig {
    @Serializable
    data object Password : LoginNavigationConfig

    @Serializable
    data class ResetPassword(
        val email: String
    ) : LoginNavigationConfig

    @Serializable
    data class ResetConfirmPassword(
        val email: String,
        val code: String
    ) : LoginNavigationConfig
}