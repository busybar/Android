package com.flipperdevices.bsb.preference.api

import com.flipperdevices.bsb.preference.model.SettingsEnum
import kotlinx.coroutines.flow.Flow

interface PreferenceApi {
    fun getFlow(key: SettingsEnum, default: Boolean = false): Flow<Boolean>
    suspend fun set(key: SettingsEnum, value: Boolean)
}