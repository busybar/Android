package com.flipperdevices.busybar.settings.viewmodel

import com.flipperdevices.busybar.core.decompose.DecomposeViewModel
import com.flipperdevices.busybar.core.theme.DarkModeSingleton
import com.flipperdevices.busybar.settings.model.SettingsEnum
import com.flipperdevices.busybar.settings.model.SettingsState
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import me.tatarka.inject.annotations.Inject

@Inject
class SettingsViewModel(
    private val settings: Settings
) : DecomposeViewModel() {
    private val stateFlow = MutableStateFlow(SettingsState())

    init {
        invalidate()
    }

    fun getState() = stateFlow.asStateFlow()

    fun onChange(settingsEnum: SettingsEnum, value: Boolean) {
        settings[settingsEnum.key] = value
        invalidate()
        if (settingsEnum == SettingsEnum.DARK_THEME) {
            DarkModeSingleton.darkMode.update { value }
        }
    }

    private fun invalidate() {
        stateFlow.update {
            SettingsState(
                devMode = settings[SettingsEnum.DEV_MODE.key, false],
                automaticFirmwareUpdate = settings[SettingsEnum.AUTO_UPDATE.key, false],
                darkTheme = settings[SettingsEnum.DARK_THEME.key, false]
            )
        }
    }
}