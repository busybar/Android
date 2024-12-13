package com.flipperdevices.bsb.auth.confirmpassword.model

sealed interface ConfirmPasswordScreenState {
    val inProgress: Boolean

    data object WaitingForInput : ConfirmPasswordScreenState {
        override val inProgress = false
    }

    data object InProgress : ConfirmPasswordScreenState {
        override val inProgress = true
    }
}