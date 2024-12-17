package com.flipperdevices.bsb.root.api

import androidx.compose.runtime.staticCompositionLocalOf
import com.flipperdevices.bsb.root.model.RootNavigationConfig

val LocalRootNavigation = staticCompositionLocalOf<RootNavigationInterface> {
    error("CompositionLocal LocalRootComponent not present")
}

interface RootNavigationInterface {
    fun push(config: RootNavigationConfig)
}
