package com.flipperdevices.busybar.device.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.device_bluetooth_title
import busystatusbar.composeapp.generated.resources.device_status_connected
import busystatusbar.composeapp.generated.resources.device_wifi_title
import busystatusbar.composeapp.generated.resources.ic_bluetooth
import busystatusbar.composeapp.generated.resources.ic_navigation
import busystatusbar.composeapp.generated.resources.ic_wifi
import com.flipperdevices.busybar.core.ktx.clickableRipple
import com.flipperdevices.busybar.core.theme.LocalBusyBarFonts
import com.flipperdevices.busybar.core.theme.LocalPallet
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun RemoteButtonsComposable(
    modifier: Modifier,
    onClick: () -> Unit
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(8.dp)
) {
    RemoteButtonComposable(
        modifier = Modifier.weight(1f).clickableRipple(onClick = onClick),
        color = LocalPallet.current.wifi.primary,
        title = Res.string.device_wifi_title,
        icon = Res.drawable.ic_wifi
    )

    RemoteButtonComposable(
        modifier = Modifier.weight(1f).clickableRipple(onClick = onClick),
        color = LocalPallet.current.bluetooth.primary,
        title = Res.string.device_bluetooth_title,
        icon = Res.drawable.ic_bluetooth
    )
}

@Composable
private fun RemoteButtonComposable(
    modifier: Modifier,
    color: Color,
    title: StringResource,
    icon: DrawableResource
) = Row(
    modifier = modifier
        .clip(RoundedCornerShape(8.dp))
        .background(LocalPallet.current.transparent.black.quaternary)
        .padding(top = 12.dp, start = 8.dp, end = 8.dp, bottom = 12.dp),
    horizontalArrangement = Arrangement.spacedBy(10.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    Icon(
        modifier = Modifier.padding(5.dp)
            .size(32.dp),
        painter = painterResource(icon),
        contentDescription = null,
        tint = color
    )

    Column(Modifier.weight(1f)) {
        Text(
            text = stringResource(title),
            fontSize = 10.sp,
            lineHeight = 10.sp,
            fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
            fontWeight = FontWeight.W500,
            color = LocalPallet.current.invert.black,
        )
        Text(
            text = stringResource(Res.string.device_status_connected),
            fontSize = 14.sp,
            lineHeight = 14.sp,
            fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
            fontWeight = FontWeight.W500,
            color = color,
        )
    }
    Icon(
        modifier = Modifier.size(14.dp),
        painter = painterResource(Res.drawable.ic_navigation),
        contentDescription = null,
        tint = LocalPallet.current.neutral.tertiary
    )
}