package com.flipperdevices.bsb.auth.within.oauth.model

import busystatusbar.components.bsb.auth.within.oauth.impl.generated.resources.Res
import busystatusbar.components.bsb.auth.within.oauth.impl.generated.resources.ic_apple
import busystatusbar.components.bsb.auth.within.oauth.impl.generated.resources.ic_apple_dark
import busystatusbar.components.bsb.auth.within.oauth.impl.generated.resources.ic_microsoft
import org.jetbrains.compose.resources.DrawableResource

enum class InternalOAuthProvider(
    val iconId: DrawableResource,
    val darkIconId: DrawableResource = iconId
) {
    APPLE(Res.drawable.ic_apple, Res.drawable.ic_apple_dark),
    MICROSOFT(Res.drawable.ic_microsoft)
}

fun OAuthProvider.toInternal() = when (this) {
    OAuthProvider.APPLE -> InternalOAuthProvider.APPLE
    OAuthProvider.MICROSOFT -> InternalOAuthProvider.MICROSOFT
}