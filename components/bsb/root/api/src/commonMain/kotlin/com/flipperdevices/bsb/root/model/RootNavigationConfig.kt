package com.flipperdevices.bsb.root.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface RootNavigationConfig {
    data object Main : RootNavigationConfig
}