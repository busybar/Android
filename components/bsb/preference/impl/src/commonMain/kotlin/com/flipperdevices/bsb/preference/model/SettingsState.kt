package com.flipperdevices.bsb.preference.model

data class SettingsState(
    val devMode: Boolean = false,
    val automaticFirmwareUpdate: Boolean = false,
    val darkTheme: Boolean = false,
)