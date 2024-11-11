package com.flipperdevices.busybar.auth.signup.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface SignUpNavigationConfig {
    data object Password : SignUpNavigationConfig
}