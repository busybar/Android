package com.flipperdevices.bsb.auth.within.oauth.api

import android.content.Context
import android.content.Intent
import com.flipperdevices.bsb.deeplink.api.DeepLinkParserDelegate
import com.flipperdevices.bsb.deeplink.api.SCHEME_BUSYAPP
import com.flipperdevices.bsb.deeplink.model.DeepLinkParserDelegatePriority
import com.flipperdevices.bsb.deeplink.model.Deeplink
import com.flipperdevices.core.di.AppGraph
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding


@Inject
@ContributesBinding(AppGraph::class, DeepLinkParserDelegate::class, multibinding = true)
class OAuthDeeplinkParser : DeepLinkParserDelegate {
    override fun getPriority(context: Context, intent: Intent): DeepLinkParserDelegatePriority? {
        if (intent.scheme != SCHEME_BUSYAPP) {
            return null
        }
        //if (intent)
    }

    override suspend fun fromIntent(context: Context, intent: Intent): Deeplink? {
        TODO("Not yet implemented")
    }

}