package com.flipperdevices.bsb.deeplink.api

import android.content.Context
import android.content.Intent
import com.flipperdevices.bsb.deeplink.model.Deeplink

/**
 * Helper for build deeplink object from Intent
 */
interface DeepLinkParser {
    /**
     * Can be called only from permission owner for intent
     */
    @Throws(SecurityException::class)
    suspend fun fromIntent(context: Context, intent: Intent): Deeplink?
}