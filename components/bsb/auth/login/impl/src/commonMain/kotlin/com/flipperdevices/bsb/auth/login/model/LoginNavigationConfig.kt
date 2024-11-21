package com.flipperdevices.bsb.auth.login.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface LoginNavigationConfig {
    @Serializable
    data object Password : LoginNavigationConfig
}