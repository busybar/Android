package com.flipperdevices.busybar.auth.main.viewmodel

import com.arkivanov.decompose.router.stack.StackNavigation
import com.flipperdevices.busybar.auth.common.model.AuthRootNavigationConfig
import com.flipperdevices.busybar.auth.main.model.AuthMainState
import com.flipperdevices.busybar.core.decompose.DecomposeViewModel
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class AuthMainViewModel(
    @Assisted private val navigationStack: StackNavigation<AuthRootNavigationConfig>,
    private val client: HttpClient
) : DecomposeViewModel() {
    private val state = MutableStateFlow<AuthMainState>(AuthMainState.WaitingForInput)

    fun getState() = state.asStateFlow()

    fun onLogin() {

    }
}