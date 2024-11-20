package com.flipperdevices.bsb.auth.main.model

sealed interface AuthMainState {
    data object WaitingForInput : AuthMainState

    data object AuthInProgress : AuthMainState
}