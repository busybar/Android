package com.flipperdevices.ui.decompose.statusbar

interface StatusBarIconStyleProvider {
    fun isStatusBarIconLight(systemIsDark: Boolean): Boolean
}
