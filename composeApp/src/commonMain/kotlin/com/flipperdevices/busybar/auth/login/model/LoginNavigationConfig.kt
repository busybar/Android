package com.flipperdevices.busybar.auth.login.model

import com.flipperdevices.busybar.auth.signup.model.SignUpNavigationConfig
import kotlinx.serialization.Serializable

@Serializable
sealed interface LoginNavigationConfig {
    data object Password : LoginNavigationConfig
}