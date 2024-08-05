package com.flipperdevices.busybar.apps.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.apps_create_status
import busystatusbar.composeapp.generated.resources.ic_plus
import com.flipperdevices.busybar.core.theme.LocalBusyBarFonts
import com.flipperdevices.busybar.core.theme.LocalPallet
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun CreateStatusComposable(
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier.height(30.dp)
        .fillMaxWidth(),
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically
) {
    Icon(
        modifier = Modifier.size(18.dp),
        painter = painterResource(Res.drawable.ic_plus),
        contentDescription = stringResource(Res.string.apps_create_status),
        tint = LocalPallet.current.brand.primary
    )
    Text(
        modifier = Modifier.padding(start = 8.dp),
        text = stringResource(Res.string.apps_create_status),
        fontSize = 16.sp,
        fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
        fontWeight = FontWeight.W500,
        color = LocalPallet.current.brand.primary
    )
}