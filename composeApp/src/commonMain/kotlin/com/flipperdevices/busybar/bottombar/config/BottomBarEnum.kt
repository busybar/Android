package com.flipperdevices.busybar.bottombar.config

import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.ic_apps
import busystatusbar.composeapp.generated.resources.ic_cloud
import busystatusbar.composeapp.generated.resources.ic_device
import busystatusbar.composeapp.generated.resources.tab_apps_title
import busystatusbar.composeapp.generated.resources.tab_device_title
import busystatusbar.composeapp.generated.resources.tab_login_title
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class BottomBarEnum(
    val tabName: StringResource,
    val icon: DrawableResource
) {
    LOGIN(Res.string.tab_login_title, Res.drawable.ic_cloud),
    DEVICE(Res.string.tab_device_title, Res.drawable.ic_device),
    APPS(Res.string.tab_apps_title, Res.drawable.ic_apps)
}