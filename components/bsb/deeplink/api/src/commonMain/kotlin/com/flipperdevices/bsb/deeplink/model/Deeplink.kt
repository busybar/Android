package com.flipperdevices.bsb.deeplink.model

import com.flipperdevices.bsb.appblocker.model.ApplicationInfo
import kotlinx.serialization.Serializable

@Serializable
sealed interface Deeplink {

    @Serializable
    sealed interface Root : Deeplink {
        @Serializable
        data class AppLockScreen(val applicationInfo: ApplicationInfo) : Root

        @Serializable
        sealed interface Auth : Root {
            @Serializable
            data class OAuth(val token: String) : Root
        }
    }
}