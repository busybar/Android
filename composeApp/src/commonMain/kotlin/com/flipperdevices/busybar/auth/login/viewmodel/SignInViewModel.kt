package com.flipperdevices.busybar.auth.login.viewmodel

import com.flipperdevices.busybar.auth.login.model.LoginState
import com.flipperdevices.busybar.cloud.api.BSBAuthApi
import com.flipperdevices.busybar.core.decompose.DecomposeViewModel
import com.flipperdevices.busybar.core.log.LogTagProvider
import com.flipperdevices.busybar.core.log.error
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class SignInViewModel(
    @Assisted private val email: String,
    @Assisted private val onComplete: () -> Unit,
    private val bsbAuthApi: BSBAuthApi
) : DecomposeViewModel(), LogTagProvider {
    override val TAG = "SignInViewModel"

    private var state = MutableStateFlow<LoginState>(LoginState.WaitingForInput)

    fun getState() = state.asStateFlow()

    fun onLogin(password: String) = viewModelScope.launch {
        state.emit(LoginState.AuthInProgress)
        bsbAuthApi.signIn(email, password).onSuccess {
            withContext(Dispatchers.Main) {
                onComplete()
            }
        }.onFailure {
            error(it) { "Failure auth for $email" }
        }
        state.emit(LoginState.WaitingForInput)
    }
}