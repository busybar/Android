package com.flipperdevices.bsb.auth.confirmpassword.viewmodel

import com.flipperdevices.bsb.auth.confirmpassword.model.ConfirmPasswordScreenState
import com.flipperdevices.bsb.auth.confirmpassword.model.InternalConfirmPasswordType
import com.flipperdevices.bsb.auth.confirmpassword.model.PasswordFieldsState
import com.flipperdevices.core.ui.lifecycle.DecomposeViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class ConfirmPasswordViewModel(
    @Assisted confirmPasswordType: InternalConfirmPasswordType
) : DecomposeViewModel() {
    private val screenState = MutableStateFlow<ConfirmPasswordScreenState>(
        ConfirmPasswordScreenState.WaitingForInput
    )

    fun getState() = screenState.asStateFlow()

    fun onPressConfirm(fieldsState: PasswordFieldsState) {

    }
}