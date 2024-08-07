package com.flipperdevices.busybar.apps.composable.apps

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.apps_block_customize
import busystatusbar.composeapp.generated.resources.apps_block_run
import busystatusbar.composeapp.generated.resources.ic_navigation
import busystatusbar.composeapp.generated.resources.login_btn
import com.flipperdevices.busybar.core.ktx.clickableRipple
import com.flipperdevices.busybar.core.theme.LocalBusyBarFonts
import com.flipperdevices.busybar.core.theme.LocalPallet
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun BusyBarAppComposable(
    modifier: Modifier = Modifier,
    app: BusyBarApp
) = Column(
    modifier = modifier.padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(20.dp)
) {
    BusyBarAppHeaderComposable(
        icon = app.icon,
        title = app.title
    )
    BusyBarAppPreviewComposable(
        modifier = Modifier.fillMaxWidth(),
        busyBarApp = app
    )

    Text(
        modifier = Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(LocalPallet.current.brand.primary)
            .padding(vertical = 14.dp),
        text = stringResource(Res.string.apps_block_run),
        fontSize = 16.sp,
        fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
        fontWeight = FontWeight.W500,
        textAlign = TextAlign.Center,
        color = LocalPallet.current.onColor.white
    )
}

@Composable
private fun BusyBarAppHeaderComposable(
    icon: DrawableResource,
    title: StringResource
) = Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(10.dp)
) {
    Icon(
        modifier = Modifier.size(26.dp),
        painter = painterResource(icon),
        contentDescription = stringResource(title),
        tint = LocalPallet.current.invert.black
    )
    Text(
        modifier = Modifier.weight(1f),
        text = stringResource(title),
        fontSize = 18.sp,
        fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
        fontWeight = FontWeight.W400,
        color = LocalPallet.current.invert.black,
    )
}