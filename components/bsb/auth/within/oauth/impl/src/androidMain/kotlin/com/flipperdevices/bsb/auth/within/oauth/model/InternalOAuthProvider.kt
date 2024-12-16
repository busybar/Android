package com.flipperdevices.bsb.auth.within.oauth.model

import busystatusbar.components.bsb.auth.within.oauth.impl.generated.resources.Res
import busystatusbar.components.bsb.auth.within.oauth.impl.generated.resources.ic_apple
import busystatusbar.components.bsb.auth.within.oauth.impl.generated.resources.ic_apple_dark
import busystatusbar.components.bsb.auth.within.oauth.impl.generated.resources.ic_microsoft
import com.flipperdevices.bsb.auth.within.main.model.AuthWay
import com.flipperdevices.bsb.cloud.model.BSBOAuthWebProvider
import org.jetbrains.compose.resources.DrawableResource

enum class InternalOAuthProvider(
    val iconId: DrawableResource,
    val darkIconId: DrawableResource = iconId,
    val authWay: AuthWay,
    val apiProvider: BSBOAuthWebProvider
) {
    APPLE(
        iconId = Res.drawable.ic_apple,
        darkIconId = Res.drawable.ic_apple_dark,
        authWay = AuthWay.APPLE,
        apiProvider = BSBOAuthWebProvider.APPLE
    ),
    MICROSOFT(
        iconId = Res.drawable.ic_microsoft,
        authWay = AuthWay.MICROSOFT,
        apiProvider = BSBOAuthWebProvider.MICROSOFT
    )
}

fun OAuthProvider.toInternal() = when (this) {
    OAuthProvider.APPLE -> InternalOAuthProvider.APPLE
    OAuthProvider.MICROSOFT -> InternalOAuthProvider.MICROSOFT
}