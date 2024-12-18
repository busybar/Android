package com.flipperdevices.bsb.auth.otp.screen.viewmodel

import com.flipperdevices.bsb.auth.otp.screen.model.AuthOtpExpiryState
import com.flipperdevices.bsb.auth.otp.screen.model.AuthOtpScreenState
import com.flipperdevices.bsb.auth.otp.screen.model.InternalAuthOtpType
import com.flipperdevices.bsb.cloud.api.BSBAuthApi
import com.flipperdevices.core.data.timer.TimerState
import com.flipperdevices.core.log.LogTagProvider
import com.flipperdevices.core.ui.lifecycle.DecomposeViewModel
import com.flipperdevices.inappnotification.api.InAppNotificationStorage
import com.flipperdevices.inappnotification.api.model.InAppNotification
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import kotlin.time.Duration.Companion.seconds

@Inject
class AuthOtpScreenViewModel(
    @Assisted private val otpType: InternalAuthOtpType,
    @Assisted private val onOtpComplete: suspend (String) -> Unit,
    @Assisted private val onFocus: suspend () -> Unit,
    @Assisted private val prefilledCode: String?,
    private val bsbAuthApi: BSBAuthApi,
    private val inAppNotificationStorage: InAppNotificationStorage
) : DecomposeViewModel(), LogTagProvider {
    override val TAG = "AuthOtpScreenViewModel"

    private val codeExpiryTimeFlow = MutableStateFlow<Instant?>(null)
    private val state = MutableStateFlow<AuthOtpScreenState>(
        AuthOtpScreenState.RequestEmailInProgress(launchedManually = false)
    )
    private val expiryTimerState = MutableStateFlow<AuthOtpExpiryState>(AuthOtpExpiryState.Empty)

    init {
        viewModelScope.launch {
            if (prefilledCode != null) {
                onCodeApplyInternal(prefilledCode)
            }

            codeExpiryTimeFlow.flatMapLatest { expiryTime ->
                if (expiryTime == null) {
                    flow {
                        requestEmailVerifyInternal(requestedManually = false)
                    }
                } else {
                    getClockFlow(expiryTime)
                }
            }.collect()
        }
    }

    fun getState() = state.asStateFlow()

    fun getExpiryTimerState() = expiryTimerState.asStateFlow()

    fun onCodeApply(otpCode: String) = viewModelScope.launch {
        onCodeApplyInternal(otpCode)
    }

    private suspend fun onCodeApplyInternal(otpCode: String) {
        val originalState = state.getAndUpdate {
            AuthOtpScreenState.CheckCodeInProgress
        }

        bsbAuthApi.checkCode(
            otpType.email,
            otpCode,
            otpType.verificationEmailType
        ).onSuccess {
            withContext(Dispatchers.Main) {
                onOtpComplete(otpCode)
            }
            state.emit(originalState)
        }.onFailure {
            state.emit(AuthOtpScreenState.WaitingForInput(wrongCodeInvalid = true))
        }
    }

    fun onReset() = viewModelScope.launch {
        requestEmailVerifyInternal(requestedManually = true)
    }

    private suspend fun requestEmailVerifyInternal(requestedManually: Boolean) {
        val originalState = state.getAndUpdate {
            AuthOtpScreenState.RequestEmailInProgress(requestedManually)
        }
        expiryTimerState.update {
            if (it is AuthOtpExpiryState.Error) {
                AuthOtpExpiryState.Empty
            } else {
                it
            }
        }
        bsbAuthApi.requestVerifyEmail(otpType.email, otpType.verificationEmailType)
            .onSuccess {
                codeExpiryTimeFlow.emit(it.codeExpiryTime)
                state.emit(AuthOtpScreenState.WaitingForInput(wrongCodeInvalid = false))
                withContext(Dispatchers.Main) {
                    onFocus()
                }
            }.onFailure {
                val originalExpiryState = expiryTimerState.getAndUpdate { expiryState ->
                    if (expiryState is AuthOtpExpiryState.Ready) {
                        expiryState
                    } else {
                        AuthOtpExpiryState.Error
                    }
                }
                if (originalExpiryState is AuthOtpExpiryState.Ready) {
                    inAppNotificationStorage.addNotification(InAppNotification.ErrorEmailSend())
                }
                if (originalState is AuthOtpScreenState.WaitingForInput) {
                    state.emit(originalState)
                } else {
                    state.emit(AuthOtpScreenState.WaitingForInput(wrongCodeInvalid = false))
                }
            }
    }

    private fun getClockFlow(expiryTime: Instant) = flow {
        while (true) {
            emit(expiryTime)
            delay(1.seconds)
        }
    }.onEach {
        val now = Clock.System.now()
        if (now > expiryTime) {
            state.update {
                if (it is AuthOtpScreenState.WaitingForInput) {
                    AuthOtpScreenState.ExpiryVerificationCode
                } else {
                    it
                }
            }
        } else {
            expiryTimerState.emit(AuthOtpExpiryState.Ready(TimerState(expiryTime - now)))
        }
    }
}
