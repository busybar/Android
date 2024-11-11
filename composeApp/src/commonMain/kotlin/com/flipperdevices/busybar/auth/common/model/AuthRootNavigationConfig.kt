package com.flipperdevices.busybar.auth.common.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface AuthRootNavigationConfig {
    data object Main : AuthRootNavigationConfig

    data class Login(val email: String) : AuthRootNavigationConfig

    data object SignUp : AuthRootNavigationConfig
}