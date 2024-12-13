package com.flipperdevices.bsb.auth.confirmpassword.viewmodel

import com.flipperdevices.bsb.auth.confirmpassword.model.ConfirmPasswordScreenState
import com.flipperdevices.bsb.auth.confirmpassword.model.InternalConfirmPasswordType
import com.flipperdevices.bsb.auth.confirmpassword.model.PasswordFieldsState
import com.flipperdevices.bsb.cloud.api.BSBAuthApi
import com.flipperdevices.core.ui.lifecycle.DecomposeViewModel
import com.flipperdevices.inappnotification.api.InAppNotificationStorage
import com.flipperdevices.inappnotification.api.model.InAppNotification
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class ConfirmPasswordViewModel(
    @Assisted private val confirmPasswordType: InternalConfirmPasswordType,
    @Assisted private val otpCode: String,
    @Assisted private val onComplete: () -> Unit,
    private val authApi: BSBAuthApi,
    private val inAppNotificationStorage: InAppNotificationStorage
) : DecomposeViewModel() {
    private val screenState = MutableStateFlow<ConfirmPasswordScreenState>(
        ConfirmPasswordScreenState.WaitingForInput
    )

    fun getState() = screenState.asStateFlow()

    fun onPressConfirm(fieldsState: PasswordFieldsState) = viewModelScope.launch {
        screenState.emit(ConfirmPasswordScreenState.InProgress)
        val result = when (confirmPasswordType) {
            is InternalConfirmPasswordType.ResetPassword -> authApi.resetPassword(
                email = confirmPasswordType.email,
                code = otpCode,
                password = fieldsState.passwordField.text
            )

            is InternalConfirmPasswordType.SignUpPassword -> authApi.signUp(
                email = confirmPasswordType.email,
                code = otpCode,
                password = fieldsState.passwordField.text
            )
        }

        result.onSuccess {
            onComplete()
        }.onFailure {
            inAppNotificationStorage.addNotification(
                InAppNotification.Error(
                    title = confirmPasswordType.errorPopUpTitle,
                    desc = confirmPasswordType.errorPopUpDesc
                )
            )
        }

        screenState.emit(ConfirmPasswordScreenState.WaitingForInput)
    }
}