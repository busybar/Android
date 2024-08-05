package com.flipperdevices.busybar.bottombar.config

import kotlinx.serialization.Serializable

@Serializable
sealed class BottomBarConfig {
    abstract val enum: BottomBarEnum

    @Serializable
    data object Login : BottomBarConfig() {
        override val enum = BottomBarEnum.LOGIN
    }

    @Serializable
    data object Device : BottomBarConfig() {
        override val enum = BottomBarEnum.DEVICE
    }

    @Serializable
    data object Apps : BottomBarConfig() {
        override val enum = BottomBarEnum.APPS
    }
}