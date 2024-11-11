package com.flipperdevices.busybar.auth.common.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface AuthRootNavigationConfig {
    data object Main : AuthRootNavigationConfig

    data object Login : AuthRootNavigationConfig

    data object SignUp : AuthRootNavigationConfig
}