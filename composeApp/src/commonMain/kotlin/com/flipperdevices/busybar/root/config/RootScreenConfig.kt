package com.flipperdevices.busybar.root.config

import kotlinx.serialization.Serializable

@Serializable
enum class RootScreenConfig {
    SEARCH,
    DEVICE,
    APPS,
    SETTINGS
}