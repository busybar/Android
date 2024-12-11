package com.flipperdevices.bsb.auth.login.viewmodel

import com.flipperdevices.bsb.cloud.api.BSBAuthApi
import com.flipperdevices.bsb.preference.api.PreferenceApi
import com.flipperdevices.bsb.preference.api.set
import com.flipperdevices.bsb.preference.model.SettingsEnum
import com.flipperdevices.bsb.auth.login.model.LoginState
import com.flipperdevices.bsb.cloud.model.BSBEmailVerificationType
import com.flipperdevices.core.ktx.common.transform
import com.flipperdevices.core.log.LogTagProvider
import com.flipperdevices.core.log.error
import com.flipperdevices.core.ui.lifecycle.DecomposeViewModel
import com.flipperdevices.inappnotification.api.InAppNotificationStorage
import com.flipperdevices.inappnotification.api.model.InAppNotification
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.Instant
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class SignInViewModel(
    @Assisted private val email: String,
    @Assisted private val onComplete: () -> Unit,
    @Assisted private val onForgetPassword: (email: String, codeExpiryTime: Instant) -> Unit,
    private val settings: PreferenceApi,
    private val bsbAuthApi: BSBAuthApi,
    private val inAppNotificationStorage: InAppNotificationStorage
) : DecomposeViewModel(), LogTagProvider {
    override val TAG = "SignInViewModel"

    private var state = MutableStateFlow<LoginState>(LoginState.WaitingForInput)

    fun getState() = state.asStateFlow()

    fun onLogin(password: String) = viewModelScope.launch {
        state.emit(LoginState.AuthInProgress)
        bsbAuthApi.signIn(email, password)
            .transform {
                bsbAuthApi.getUser()
            }
            .onSuccess { user ->
                settings.set(SettingsEnum.USER_DATA, user)
                withContext(Dispatchers.Main) {
                    onComplete()
                }
            }.onFailure {
                error(it) { "Failure auth for $email" }
            }
        state.emit(LoginState.WaitingForInput)
    }

    fun onForgotPassword(email: String) = viewModelScope.launch {
        state.emit(LoginState.AuthInProgress)
        bsbAuthApi.requestVerifyEmail(email, BSBEmailVerificationType.RESET_PASSWORD)
            .onSuccess {
                withContext(Dispatchers.Main) {
                    onForgetPassword(email, it.codeExpiryTime)
                }
            }.onFailure {
                inAppNotificationStorage.addNotification(InAppNotification.ErrorEmailSend())
            }
        state.emit(LoginState.WaitingForInput)
    }
}