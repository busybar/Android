package com.flipperdevices.bsb.auth.main.model

import com.flipperdevices.bsb.auth.within.oauth.model.OAuthProvider
import com.flipperdevices.bsb.deeplink.model.Deeplink
import kotlinx.serialization.Serializable

@Serializable
sealed interface AuthRootNavigationConfig {
    @Serializable
    data class AuthRoot(val deeplink: Deeplink.Root.Auth?) : AuthRootNavigationConfig

    @Serializable
    data class LogIn(val email: String, val preFilledPassword: String?) : AuthRootNavigationConfig

    @Serializable
    data class SignUp(val email: String, val preFilledPassword: String?) : AuthRootNavigationConfig

    @Serializable
    data class WebView(val oAuthProvider: OAuthProvider) : AuthRootNavigationConfig
}