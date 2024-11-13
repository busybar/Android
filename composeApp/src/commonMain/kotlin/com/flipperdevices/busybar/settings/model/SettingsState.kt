package com.flipperdevices.busybar.settings.model

data class SettingsState(
    val devMode: Boolean = false,
    val automaticFirmwareUpdate: Boolean = false,
    val darkTheme: Boolean = false,
)