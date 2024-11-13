package com.flipperdevices.busybar.device.composable.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.device_login_logout
import busystatusbar.composeapp.generated.resources.ic_user
import busystatusbar.composeapp.generated.resources.ic_user_dark
import com.flipperdevices.busybar.core.theme.LocalBusyBarFonts
import com.flipperdevices.busybar.core.theme.LocalPallet
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun LogInRowComposable(
    modifier: Modifier,
    email: String,
    onLogout: () -> Unit
) {
    Row(
        modifier.clip(RoundedCornerShape(8.dp))
            .background(LocalPallet.current.transparent.blackInvert.quaternary)
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(
                if (MaterialTheme.colors.isLight) {
                    Res.drawable.ic_user
                } else {
                    Res.drawable.ic_user_dark
                }
            ),
            contentDescription = null
        )
        Text(
            modifier = Modifier.weight(1f),
            text = email,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
            fontSize = 16.sp,
            fontWeight = FontWeight.W600,
            color = LocalPallet.current.black.invert
        )
        Text(
            modifier = Modifier.clickable(onClick = onLogout),
            text = stringResource(Res.string.device_login_logout),
            fontSize = 10.sp,
            fontWeight = FontWeight.W600,
            color = LocalPallet.current.neutral.secondary,
            fontFamily = LocalBusyBarFonts.current.ppNeueMontreal
        )

    }
}