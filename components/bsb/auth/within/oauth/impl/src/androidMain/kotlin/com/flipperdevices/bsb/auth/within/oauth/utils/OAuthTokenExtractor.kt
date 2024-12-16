package com.flipperdevices.bsb.auth.within.oauth.utils

import android.net.Uri
import com.flipperdevices.bsb.cloud.model.BSBOAuthInformation

object OAuthTokenExtractor {
    fun extract(url: String, oauthInformation: BSBOAuthInformation): String? {
        val parsedUrl = runCatching { Uri.parse(url) }.getOrNull() ?: return null
        return parsedUrl.getQueryParameter(oauthInformation.tokenQueryKey)
    }
}