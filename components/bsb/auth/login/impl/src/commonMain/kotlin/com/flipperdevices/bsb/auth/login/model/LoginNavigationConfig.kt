package com.flipperdevices.bsb.auth.login.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface LoginNavigationConfig {
    data object Password : LoginNavigationConfig
}