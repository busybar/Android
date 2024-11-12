package com.flipperdevices.busybar.auth.main.viewmodel

import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pushNew
import com.flipperdevices.busybar.auth.common.model.AuthRootNavigationConfig
import com.flipperdevices.busybar.auth.main.model.AuthMainState
import com.flipperdevices.busybar.cloud.api.BSBAuthApi
import com.flipperdevices.busybar.core.decompose.DecomposeViewModel
import com.flipperdevices.busybar.core.log.error
import com.flipperdevices.busybar.core.log.info
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class AuthMainViewModel(
    @Assisted private val navigationStack: StackNavigation<AuthRootNavigationConfig>,
    private val authApi: BSBAuthApi
) : DecomposeViewModel() {
    private val state = MutableStateFlow<AuthMainState>(AuthMainState.WaitingForInput)

    fun getState() = state.asStateFlow()

    fun onLogin(email: String) {
        info { "Start check email..." }
        viewModelScope.launch {
            state.emit(AuthMainState.AuthInProgress)
            authApi.isUserExist(email).onSuccess { userExist ->
                withContext(Dispatchers.Main) {
                    if (userExist) {
                        navigationStack.pushNew(AuthRootNavigationConfig.Login(email))
                    } else {
                        navigationStack.pushNew(AuthRootNavigationConfig.SignUp)
                    }
                }
            }.onFailure {
                error(it) { "Fail login with $email" }
            }
            state.emit(AuthMainState.WaitingForInput)
        }
    }
}