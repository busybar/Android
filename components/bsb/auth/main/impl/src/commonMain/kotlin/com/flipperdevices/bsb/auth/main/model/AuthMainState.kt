package com.flipperdevices.bsb.auth.main.model

import com.flipperdevices.bsb.auth.within.main.model.AuthWay

sealed interface AuthMainState {
    data object WaitingForInput : AuthMainState

    data class AuthInProgress(val authWay: AuthWay) : AuthMainState
}