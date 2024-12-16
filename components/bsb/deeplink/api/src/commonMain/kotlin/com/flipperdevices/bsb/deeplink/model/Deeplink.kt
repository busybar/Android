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
            sealed interface OAuth : Auth {
                val token: String

                @Serializable
                data class Microsoft(override val token: String) : OAuth

                @Serializable
                data class Apple(override val token: String) : OAuth
            }
        }
    }
}