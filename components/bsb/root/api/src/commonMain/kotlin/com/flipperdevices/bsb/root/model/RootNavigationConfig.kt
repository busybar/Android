package com.flipperdevices.bsb.root.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface RootNavigationConfig {
    @Serializable
    data object Main : RootNavigationConfig
}