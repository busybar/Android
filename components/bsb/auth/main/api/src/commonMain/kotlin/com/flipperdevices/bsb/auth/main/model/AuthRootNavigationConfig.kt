package com.flipperdevices.bsb.auth.main.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface AuthRootNavigationConfig {
    @Serializable
    data object AuthRoot : AuthRootNavigationConfig

    @Serializable
    data class LogIn(val email: String) : AuthRootNavigationConfig

    @Serializable
    data object SignUp : AuthRootNavigationConfig
}