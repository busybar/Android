package com.flipperdevices.bsb.auth.within.oauth.api

import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.bsb.auth.within.oauth.model.OAuthProvider
import com.flipperdevices.ui.decompose.ScreenDecomposeComponent

abstract class OAuthWebViewDecomposeComponent(
    componentContext: ComponentContext
) : ScreenDecomposeComponent(componentContext) {
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            oAuthProvider: OAuthProvider,
            onComplete: (String) -> Unit
        ): OAuthWebViewDecomposeComponent
    }
}
