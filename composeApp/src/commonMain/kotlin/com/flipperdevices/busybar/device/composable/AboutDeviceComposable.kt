package com.flipperdevices.busybar.device.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.device_btn_change_app
import busystatusbar.composeapp.generated.resources.device_btn_update_firmware
import busystatusbar.composeapp.generated.resources.device_section_about_title
import busystatusbar.composeapp.generated.resources.ic_navigation
import com.flipperdevices.busybar.core.ktx.clickableRipple
import com.flipperdevices.busybar.core.theme.LocalBusyBarFonts
import com.flipperdevices.busybar.core.theme.LocalPallet
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AboutDeviceComposable(
    modifier: Modifier,
    onClick: () -> Unit
) = Column(
    modifier,
    verticalArrangement = Arrangement.spacedBy(12.dp)
) {
    Text(
        text = stringResource(Res.string.device_section_about_title),
        fontSize = 12.sp,
        fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
        fontWeight = FontWeight.W400,
        color = LocalPallet.current.neutral.tertiary,
    )
    UpdateFirmwareButton(
        Modifier.fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickableRipple(onClick = onClick)
    )
    AboutDeviceRowsComposable(Modifier)
}

@Composable
private fun UpdateFirmwareButton(modifier: Modifier) {
    Row(
        modifier
            .border(2.dp, LocalPallet.current.brand.primary, RoundedCornerShape(8.dp))
            .padding(vertical = 14.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(Res.string.device_btn_update_firmware),
            fontSize = 16.sp,
            fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
            fontWeight = FontWeight.W500,
            color = LocalPallet.current.brand.primary,
        )
        Icon(
            modifier = Modifier
                .padding(start = 2.dp)
                .size(14.dp),
            painter = painterResource(Res.drawable.ic_navigation),
            contentDescription = null,
            tint = LocalPallet.current.brand.primary
        )
    }
}