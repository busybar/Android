package com.flipperdevices.bsb.root.model

import com.flipperdevices.bsb.appblocker.model.ApplicationInfo
import kotlinx.serialization.Serializable

@Serializable
sealed interface RootNavigationConfig {
    @Serializable
    data object Timer : RootNavigationConfig

    @Serializable
    data object Auth : RootNavigationConfig

    @Serializable
    data object Settings : RootNavigationConfig

    @Serializable
    data class AppLockScreen(val applicationInfo: ApplicationInfo) : RootNavigationConfig
}