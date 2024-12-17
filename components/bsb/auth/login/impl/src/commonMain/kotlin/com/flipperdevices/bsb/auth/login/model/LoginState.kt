package com.flipperdevices.bsb.auth.login.model

sealed interface LoginState {
    data object WaitingForInput : LoginState

    data object AuthInProgress : LoginState
}
