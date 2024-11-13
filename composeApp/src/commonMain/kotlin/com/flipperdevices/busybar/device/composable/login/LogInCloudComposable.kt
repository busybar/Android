package com.flipperdevices.busybar.device.composable.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.device_login_login
import busystatusbar.composeapp.generated.resources.ic_busycloud_login
import com.flipperdevices.busybar.core.ktx.clickableRipple
import com.flipperdevices.busybar.core.theme.LocalBusyBarFonts
import com.flipperdevices.busybar.core.theme.LocalPallet
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun LogInCloudComposable(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .size(48.dp)
            .background(LocalPallet.current.transparent.blackInvert.quaternary)
            .clickableRipple(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(32.dp),
            painter = painterResource(Res.drawable.ic_busycloud_login),
            contentDescription = null,
            tint = LocalPallet.current.neutral.secondary
        )

        Text(
            text = stringResource(Res.string.device_login_login),
            fontSize = 10.sp,
            fontWeight = FontWeight.W600,
            color = LocalPallet.current.neutral.secondary,
            fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
            lineHeight = 10.sp
        )
    }
}