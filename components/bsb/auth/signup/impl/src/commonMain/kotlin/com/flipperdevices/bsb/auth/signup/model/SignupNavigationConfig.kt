package com.flipperdevices.bsb.auth.signup.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface SignupNavigationConfig {
    data object Main : SignupNavigationConfig
}