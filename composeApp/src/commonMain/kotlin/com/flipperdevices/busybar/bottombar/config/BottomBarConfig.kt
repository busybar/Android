package com.lionzxy.flippertesttask.bottombar.config

import com.flipperdevices.busybar.bottombar.config.BottomBarEnum
import kotlinx.serialization.Serializable

@Serializable
sealed class BottomBarConfig {
    abstract val enum: BottomBarEnum

    @Serializable
    data object Device : BottomBarConfig() {
        override val enum = BottomBarEnum.LOGIN
    }

    @Serializable
    data object Archive : BottomBarConfig() {
        override val enum = BottomBarEnum.DEVICE
    }

    @Serializable
    data object Hub : BottomBarConfig() {
        override val enum = BottomBarEnum.APPS
    }
}