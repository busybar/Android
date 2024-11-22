package com.flipperdevices.bsb.preference.api

import com.flipperdevices.bsb.preference.model.SettingsEnum
import com.flipperdevices.core.di.AppGraph
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@Inject
@ContributesBinding(AppGraph::class, ThemeStatusBarIconStyleProvider::class)
class ThemeStatusBarIconStyleProviderImpl(
    private val preferenceApi: PreferenceApi
) : ThemeStatusBarIconStyleProvider {
    override fun isStatusBarIconLight(systemIsDark: Boolean): Boolean {
        val isDark = preferenceApi.getBoolean(SettingsEnum.DARK_THEME, null)
        return when (isDark) {
            true -> true
            false -> false
            null -> systemIsDark
        }
    }
}