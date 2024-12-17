package com.flipperdevices.bsb.timer.main.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface TimerMainNavigationConfig {
    @Serializable
    data object Main : TimerMainNavigationConfig

    @Serializable
    data object Timer : TimerMainNavigationConfig
}
