package com.flipperdevices.bsb.deeplink.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface Deeplink {

    @Serializable
    sealed interface Root : Deeplink {
        @Serializable
        data class AppLockScreen(val appPackage: String) : Root
    }
}