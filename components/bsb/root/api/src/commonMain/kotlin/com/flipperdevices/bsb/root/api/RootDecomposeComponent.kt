package com.flipperdevices.bsb.root.api

import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.bsb.deeplink.model.Deeplink
import com.flipperdevices.bsb.root.model.RootNavigationConfig
import com.flipperdevices.ui.decompose.CompositeDecomposeComponent

abstract class RootDecomposeComponent :
    CompositeDecomposeComponent<RootNavigationConfig>(),
    RootNavigationInterface,
    RootDeeplinkHandler {
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            initialDeeplink: Deeplink?
        ): RootDecomposeComponent
    }
}
