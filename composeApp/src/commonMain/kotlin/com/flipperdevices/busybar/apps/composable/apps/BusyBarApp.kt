package com.flipperdevices.busybar.apps.composable.apps

import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class BusyBarApp(
    val pic: DrawableResource,
    val icon: DrawableResource,
    val title: StringResource
) {
    companion object {
        val default = BusyBarApp(
            pic = Res.drawable.pic_apps_busy,
            icon = Res.drawable.ic_apps_busy,
            title = Res.string.apps_list_busy
        )
    }
}

val APPS = listOf(
    BusyBarApp.default,
    BusyBarApp(
        pic = Res.drawable.pic_apps_tomato,
        icon = Res.drawable.ic_apps_tomato,
        title = Res.string.apps_list_tomato
    ),
    BusyBarApp(
        pic = Res.drawable.pic_apps_wallpapers,
        icon = Res.drawable.ic_apps_wallpapers,
        title = Res.string.apps_list_wallpapers
    ),
    BusyBarApp(
        pic = Res.drawable.pic_apps_weather,
        icon = Res.drawable.ic_apps_clock,
        title = Res.string.apps_list_clock
    ),
    BusyBarApp(
        pic = Res.drawable.pic_apps_instagram,
        icon = Res.drawable.ic_apps_instagram,
        title = Res.string.apps_list_instagram
    ),
)