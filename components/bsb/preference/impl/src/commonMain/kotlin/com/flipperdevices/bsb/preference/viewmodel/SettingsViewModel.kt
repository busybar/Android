@file:OptIn(ExperimentalSettingsApi::class)

package com.flipperdevices.bsb.preference.viewmodel

import com.flipperdevices.bsb.preference.api.PreferenceApi
import com.flipperdevices.bsb.preference.model.SettingsEnum
import com.flipperdevices.bsb.preference.model.SettingsState
import com.flipperdevices.core.ui.lifecycle.DecomposeViewModel
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.FlowSettings
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject

@Inject
class SettingsViewModel(
    private val settings: PreferenceApi
) : DecomposeViewModel() {
    private val stateFlow = combine(
        settings.getFlow(SettingsEnum.DEV_MODE, false),
        settings.getFlow(SettingsEnum.AUTO_UPDATE, false),
        settings.getFlow(SettingsEnum.DARK_THEME, false)
    ) { devMode, automaticFirmwareUpdate, darkTheme ->
        SettingsState(
            devMode = devMode,
            automaticFirmwareUpdate = automaticFirmwareUpdate,
            darkTheme = darkTheme
        )
    }.stateIn(viewModelScope, SharingStarted.Lazily, SettingsState())

    fun getState() = stateFlow

    fun onChange(settingsEnum: SettingsEnum, value: Boolean) = viewModelScope.launch {
        settings.set(settingsEnum, value)
    }
}