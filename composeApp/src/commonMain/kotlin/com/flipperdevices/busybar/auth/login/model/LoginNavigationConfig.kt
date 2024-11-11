package com.flipperdevices.busybar.auth.login.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface LoginNavigationConfig {
    data object Password : LoginNavigationConfig
}