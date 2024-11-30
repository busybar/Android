package com.flipperdevices.bsb.preferencescreen.viewmodel

import com.flipperdevices.bsb.dnd.api.BusyDNDApi
import com.flipperdevices.bsb.preferencescreen.model.PreferenceScreenState
import com.flipperdevices.core.ui.lifecycle.DecomposeViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import me.tatarka.inject.annotations.Inject

@Inject
class PreferenceScreenViewModel(
    private val dndApi: BusyDNDApi
) : DecomposeViewModel() {
    private val state =
        MutableStateFlow(
            PreferenceScreenState(
                isDndActive = dndApi.isDNDSupportActive(),
                false
            )
        )

    fun getState() = state.asStateFlow()

    fun onDndSwitch(newState: Boolean) {
        if (newState) {
            dndApi.enableSupport()
        } else {
            dndApi.disableSupport()
        }
        state.update {
            it.copy(isDndActive = dndApi.isDNDSupportActive())
        }
    }

    fun onAppBlock(newState: Boolean) {

    }
}