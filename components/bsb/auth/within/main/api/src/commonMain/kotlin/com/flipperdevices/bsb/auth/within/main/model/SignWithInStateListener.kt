package com.flipperdevices.bsb.auth.within.main.model

fun interface SignWithInStateListener {
    operator fun invoke(withInState: SignWithInState)
}