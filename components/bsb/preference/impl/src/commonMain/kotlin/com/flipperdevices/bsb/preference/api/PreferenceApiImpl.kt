package com.flipperdevices.bsb.preference.api

import com.flipperdevices.bsb.preference.model.SettingsEnum
import com.flipperdevices.core.di.AppGraph
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.toFlowSettings
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@Inject
@SingleIn(AppGraph::class)
@ContributesBinding(AppGraph::class, PreferenceApi::class)
class PreferenceApiImpl(
    observableSettings: ObservableSettings
) : PreferenceApi {
    private val flowSettings = observableSettings.toFlowSettings()

    override fun getFlow(
        key: SettingsEnum, default: Boolean
    ) = flowSettings.getBooleanFlow(key.key, default)

    override suspend fun set(key: SettingsEnum, value: Boolean) {
        flowSettings.putBoolean(key.key, value)
    }
}