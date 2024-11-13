package com.flipperdevices.busybar.core.ktx

import com.flipperdevices.busybar.settings.model.SettingsEnum
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.FlowSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T> Settings.encodeValue(key: SettingsEnum, value: T) {
    putString(key.key, Json.encodeToString(value))
}

inline fun <reified T> Settings.decodeValue(key: SettingsEnum, default: T): T {
    return getStringOrNull(key.key)?.let { Json.decodeFromString(it) } ?: default
}

@OptIn(ExperimentalSettingsApi::class)
inline fun <reified T> FlowSettings.decodeFlow(key: SettingsEnum, default: T?): Flow<T?> {
    return getStringOrNullFlow(key.key)
        .map {
            if (it.isNullOrBlank()) {
                default
            } else {
                Json.decodeFromString(it)
            }
        }
}

@OptIn(ExperimentalSettingsApi::class)
inline fun <reified T> FlowSettings.decodeFlowOrNull(key: SettingsEnum): Flow<T?> {
    return getStringOrNullFlow(key.key)
        .map {
            if (it.isNullOrBlank()) {
                null
            } else {
                Json.decodeFromString(it)
            }
        }
}