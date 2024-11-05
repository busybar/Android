package com.flipperdevices.busybar.login.common.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface LogInNavigationConfig {
    data object Main : LogInNavigationConfig
}