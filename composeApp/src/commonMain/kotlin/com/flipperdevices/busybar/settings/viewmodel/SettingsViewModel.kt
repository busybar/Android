@file:OptIn(ExperimentalSettingsApi::class)

package com.flipperdevices.busybar.settings.viewmodel

import com.flipperdevices.busybar.core.decompose.DecomposeViewModel
import com.flipperdevices.busybar.settings.model.SettingsEnum
import com.flipperdevices.busybar.settings.model.SettingsState
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.FlowSettings
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject

@Inject
class SettingsViewModel(
    private val settings: FlowSettings
) : DecomposeViewModel() {
    private val stateFlow = combine(
        settings.getBooleanFlow(SettingsEnum.DEV_MODE.key, false),
        settings.getBooleanFlow(SettingsEnum.AUTO_UPDATE.key, false),
        settings.getBooleanFlow(SettingsEnum.DARK_THEME.key, false)
    ) { devMode, automaticFirmwareUpdate, darkTheme ->
        SettingsState(
            devMode = devMode,
            automaticFirmwareUpdate = automaticFirmwareUpdate,
            darkTheme = darkTheme
        )
    }.stateIn(viewModelScope, SharingStarted.Lazily, SettingsState())

    fun getState() = stateFlow

    fun onChange(settingsEnum: SettingsEnum, value: Boolean) = viewModelScope.launch {
        settings.putBoolean(settingsEnum.key, value)
    }
}