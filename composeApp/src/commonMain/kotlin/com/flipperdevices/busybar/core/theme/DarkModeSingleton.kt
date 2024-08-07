package com.flipperdevices.busybar.core.theme

import com.flipperdevices.busybar.core.decompose.DecomposeViewModel
import com.flipperdevices.busybar.settings.model.SettingsEnum
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import kotlinx.coroutines.flow.MutableStateFlow
import me.tatarka.inject.annotations.Inject

object DarkModeSingleton {
    val darkMode = MutableStateFlow(false)
    val devMode = MutableStateFlow(false)
}