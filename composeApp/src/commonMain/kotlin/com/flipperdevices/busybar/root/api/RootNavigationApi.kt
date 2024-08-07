package com.flipperdevices.busybar.root.api

import com.flipperdevices.busybar.apps.composable.apps.BusyBarApp
import com.flipperdevices.busybar.root.config.RootScreenConfig

interface RootNavigationApi {
    fun push(config: RootScreenConfig)

    fun onAppSelected(barApp: BusyBarApp)
}