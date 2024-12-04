package com.flipperdevices.bsb.auth.main.viewmodel

import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pushToFront
import com.flipperdevices.bsb.auth.main.model.AuthMainState
import com.flipperdevices.bsb.auth.main.model.AuthRootNavigationConfig
import com.flipperdevices.bsb.auth.within.main.model.AuthWay
import com.flipperdevices.bsb.auth.within.main.model.SignWithInState
import com.flipperdevices.bsb.auth.within.main.model.SignWithInStateListener
import com.flipperdevices.bsb.cloud.api.BSBAuthApi
import com.flipperdevices.core.log.LogTagProvider
import com.flipperdevices.core.log.error
import com.flipperdevices.core.log.info
import com.flipperdevices.core.ui.lifecycle.DecomposeViewModel
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
    @Assisted private val onComplete: () -> Unit,
    private val authApi: BSBAuthApi
) : DecomposeViewModel(), SignWithInStateListener, LogTagProvider {
    override val TAG = "AuthMainViewModel"

    private val state = MutableStateFlow<AuthMainState>(AuthMainState.WaitingForInput)

    fun getState() = state.asStateFlow()

    fun onLogin(email: String) {
        info { "Start check email..." }
        viewModelScope.launch {
            state.emit(AuthMainState.AuthInProgress(AuthWay.EMAIL))
            authApi.isUserExist(email).onSuccess { userExist ->
                withContext(Dispatchers.Main) {
                    if (userExist) {
                        navigationStack.pushToFront(AuthRootNavigationConfig.LogIn(email))
                    } else {
                        navigationStack.pushToFront(AuthRootNavigationConfig.SignUp)
                    }
                }
            }.onFailure {
                error(it) { "Fail login with $email" }
            }
            state.emit(AuthMainState.WaitingForInput)
        }
    }

    override fun invoke(withInState: SignWithInState) {
        when (withInState) {
            SignWithInState.Complete -> viewModelScope.launch(Dispatchers.Main) {
                onComplete()
            }

            is SignWithInState.InProgress -> {
                state.value = AuthMainState.AuthInProgress(withInState.authWay)
            }

            SignWithInState.WaitingForInput -> state.value = AuthMainState.WaitingForInput
        }
    }
}