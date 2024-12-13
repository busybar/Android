package com.flipperdevices.bsb.auth.otp.screen.viewmodel

import com.flipperdevices.bsb.auth.otp.screen.model.AuthOtpScreenState
import com.flipperdevices.bsb.auth.otp.screen.model.InternalAuthOtpType
import com.flipperdevices.bsb.cloud.api.BSBAuthApi
import com.flipperdevices.core.data.timer.TimerState
import com.flipperdevices.core.log.LogTagProvider
import com.flipperdevices.core.log.info
import com.flipperdevices.core.ui.lifecycle.DecomposeViewModel
import com.flipperdevices.inappnotification.api.InAppNotificationStorage
import com.flipperdevices.inappnotification.api.model.InAppNotification
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
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
    @Assisted codeExpiryTimeMs: Long,
    private val bsbAuthApi: BSBAuthApi,
    private val inAppNotificationStorage: InAppNotificationStorage
) : DecomposeViewModel(), LogTagProvider {
    override val TAG = "AuthOtpScreenViewModel"

    private val codeExpiryTime = Instant.fromEpochMilliseconds(codeExpiryTimeMs)
    private val codeExpiryTimeFlow = MutableStateFlow(codeExpiryTime)
    private val state = MutableStateFlow<AuthOtpScreenState>(
        AuthOtpScreenState.WaitingForInput(
            wrongCodeInvalid = false
        )
    )
    private val timerState = MutableStateFlow(TimerState(codeExpiryTime - Clock.System.now()))

    init {
        combine(
            flow {
                while (true) {
                    emit(Unit)
                    delay(1.seconds)
                }
            },
            codeExpiryTimeFlow
        ) { _, expiryTime ->
            val now = Clock.System.now()
            if (now > expiryTime) {
                state.update {
                    if (it is AuthOtpScreenState.WaitingForInput) {
                        AuthOtpScreenState.ExpiryVerificationCode
                    } else it
                }
            } else {
                timerState.emit(TimerState(expiryTime - now))
            }
        }.launchIn(viewModelScope)
    }

    fun getState() = state.asStateFlow()

    fun getTimerState() = timerState.asStateFlow()

    fun onCodeApply(otpCode: String) = viewModelScope.launch {
        state.emit(AuthOtpScreenState.CheckCodeInProgress)

        bsbAuthApi.checkCode(
            otpType.email,
            otpCode,
            otpType.verificationEmailType
        ).onSuccess {
            withContext(Dispatchers.Main) {
                onOtpComplete(otpCode)
            }
            state.emit(AuthOtpScreenState.WaitingForInput(wrongCodeInvalid = false))
        }.onFailure {
            state.emit(AuthOtpScreenState.WaitingForInput(wrongCodeInvalid = true))
        }
    }

    fun onReset() = viewModelScope.launch {
        state.emit(AuthOtpScreenState.ResetPasswordInProgress)
        bsbAuthApi.requestVerifyEmail(otpType.email, otpType.verificationEmailType)
            .onSuccess {
                codeExpiryTimeFlow.emit(it.codeExpiryTime)
            }.onFailure {
                inAppNotificationStorage.addNotification(InAppNotification.ErrorEmailSend())
            }
        state.emit(AuthOtpScreenState.WaitingForInput(wrongCodeInvalid = false))
    }
}