package com.flipperdevices.bsb.root.api

import com.flipperdevices.bsb.deeplink.model.Deeplink

interface RootDeeplinkHandler {
    fun handleDeeplink(deeplink: Deeplink)
}