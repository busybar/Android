package com.flipperdevices.bsb.auth.otp.screen

import android.content.Context
import android.content.Intent
import com.flipperdevices.bsb.deeplink.api.DeepLinkParserDelegate
import com.flipperdevices.bsb.deeplink.model.DeepLinkParserDelegatePriority
import com.flipperdevices.bsb.deeplink.model.Deeplink
import com.flipperdevices.core.di.AppGraph
import com.flipperdevices.core.ktx.android.toFullString
import com.flipperdevices.core.log.LogTagProvider
import com.flipperdevices.core.log.error
import com.flipperdevices.core.log.info
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

private val SUPPORTED_HOSTS = arrayOf("cloud.dev.busy.bar")
private const val PATH = "/login/email-callback"

@Inject
@ContributesBinding(AppGraph::class, DeepLinkParserDelegate::class, multibinding = true)
class DeeplinkParserVerifyEmailImpl : DeepLinkParserDelegate, LogTagProvider {
    override val TAG = "DeeplinkParserVerifyEmailImpl"

    override fun getPriority(context: Context, intent: Intent): DeepLinkParserDelegatePriority? {
        val uri = intent.data ?: return null
        if (SUPPORTED_HOSTS.contains(uri.host).not()) {
            return null
        }
        if (uri.path != PATH) {
            return null
        }

        return DeepLinkParserDelegatePriority.HIGH
    }

    override suspend fun fromIntent(context: Context, intent: Intent): Deeplink? {
        info { "Process intent ${intent.toFullString()}" }
        val uri = intent.data ?: return null
        if (SUPPORTED_HOSTS.contains(uri.host).not()) {
            return null
        }
        if (uri.path != PATH) {
            return null
        }

        val confirmType = uri.getQueryParameter("confirm_type")
        val code = uri.getQueryParameter("code")
        val email = uri.getQueryParameter("email")

        if (confirmType == null || code == null || email == null) {
            error { "Failed to parse uri $uri" }
            return null
        }

        return when (confirmType) {
            "sign-up-account" -> Deeplink.Root.Auth.VerifyEmailLink.SignUp(
                email = email,
                otpCode = code
            )

            "reset-password" -> Deeplink.Root.Auth.VerifyEmailLink.ResetPassword(
                email = email,
                otpCode = code
            )

            else -> {
                error { "Failed to find confirm type $confirmType" }
                null
            }
        }
    }
}