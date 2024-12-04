package com.flipperdevices.bsb.auth.within.oauth.api

import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.bsb.auth.within.main.api.SignWithInElementDecomposeComponent
import com.flipperdevices.bsb.auth.within.main.model.SignWithInStateListener
import com.flipperdevices.bsb.auth.within.oauth.model.OAuthProvider

abstract class OAuthScreenDecomposeComponent(
    componentContext: ComponentContext
) : SignWithInElementDecomposeComponent(componentContext) {
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            oAuthProvider: OAuthProvider,
            withInStateListener: SignWithInStateListener
        ): OAuthScreenDecomposeComponent
    }
}