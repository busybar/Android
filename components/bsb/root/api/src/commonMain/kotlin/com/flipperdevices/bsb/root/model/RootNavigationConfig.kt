package com.flipperdevices.bsb.root.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface RootNavigationConfig {
    @Serializable
    data object Timer : RootNavigationConfig
    @Serializable
    data object Auth : RootNavigationConfig
}