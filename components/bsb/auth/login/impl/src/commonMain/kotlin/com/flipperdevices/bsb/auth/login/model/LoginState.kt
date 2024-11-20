package com.flipperdevices.busybar.auth.login.model

sealed interface LoginState {
    data object WaitingForInput : LoginState

    data object AuthInProgress : LoginState
}