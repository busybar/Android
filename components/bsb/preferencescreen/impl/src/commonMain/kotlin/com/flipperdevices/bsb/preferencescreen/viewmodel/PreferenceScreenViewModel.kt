package com.flipperdevices.bsb.preferencescreen.viewmodel

import com.arkivanov.decompose.router.stack.StackNavigation
import com.flipperdevices.bsb.appblocker.api.AppBlockerApi
import com.flipperdevices.bsb.dnd.api.BusyDNDApi
import com.flipperdevices.bsb.preference.api.PreferenceApi
import com.flipperdevices.bsb.preference.api.get
import com.flipperdevices.bsb.preference.api.getFlow
import com.flipperdevices.bsb.preference.api.set
import com.flipperdevices.bsb.preference.model.SettingsEnum
import com.flipperdevices.bsb.preferencescreen.model.PreferenceScreenState
import com.flipperdevices.bsb.preferencescreen.model.SettingsAction
import com.flipperdevices.bsb.root.api.RootNavigationInterface
import com.flipperdevices.bsb.root.model.RootNavigationConfig
import com.flipperdevices.core.ui.lifecycle.DecomposeViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import me.tatarka.inject.annotations.Inject

@Inject
class PreferenceScreenViewModel(
    private val dndApi: BusyDNDApi,
    private val appBlockerApi: AppBlockerApi,
    private val preferenceApi: PreferenceApi
) : DecomposeViewModel() {
    private val state =
        MutableStateFlow(
            PreferenceScreenState(
                isDndActive = dndApi.isDNDSupportActive(),
                isAppBlockActive = appBlockerApi.isAppBlockerSupportActive(),
                devMode = preferenceApi.get(SettingsEnum.DEV_MODE, false)
            )
        )

    init {
        preferenceApi.getFlow(SettingsEnum.DEV_MODE, false)
            .onEach { devMode ->
                state.update { it.copy(devMode = devMode) }
            }.launchIn(viewModelScope)
    }

    fun getState() = state.asStateFlow()

    fun onAction(
        action: SettingsAction,
        navigation: RootNavigationInterface
    ) {
        when (action) {
            SettingsAction.ExpertMode -> preferenceApi.set(SettingsEnum.DEV_MODE, true)
            SettingsAction.OpenAuth -> navigation.push(RootNavigationConfig.Auth)
            is SettingsAction.SwitchAppBlocking -> onAppBlock(action.newState)
            is SettingsAction.SwitchDND -> onDndSwitch(action.newState)
        }
    }

    private fun onDndSwitch(newState: Boolean) {
        if (newState) {
            dndApi.enableSupport()
        } else {
            dndApi.disableSupport()
        }
        state.update {
            it.copy(isDndActive = dndApi.isDNDSupportActive())
        }
    }

    private fun onAppBlock(newState: Boolean) {
        if (newState) {
            appBlockerApi.enableSupport()
        } else {
            appBlockerApi.disableSupport()
        }
        state.update {
            it.copy(isAppBlockActive = appBlockerApi.isAppBlockerSupportActive())
        }
    }
}