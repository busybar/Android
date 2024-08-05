package com.flipperdevices.busybar.device.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.device_btn_mute
import busystatusbar.composeapp.generated.resources.device_btn_reboot
import busystatusbar.composeapp.generated.resources.device_btn_turn_off
import busystatusbar.composeapp.generated.resources.ic_settings
import busystatusbar.composeapp.generated.resources.login_btn
import com.flipperdevices.busybar.core.theme.LocalBusyBarFonts
import com.flipperdevices.busybar.core.theme.LocalPallet
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ControlButtonsComposable(modifier: Modifier) {
    Column(modifier) {
        Text(
            modifier = Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(LocalPallet.current.transparent.black.quaternary)
                .padding(vertical = 18.dp, horizontal = 32.dp),
            text = stringResource(Res.string.device_btn_turn_off),
            fontSize = 16.sp,
            fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
            fontWeight = FontWeight.W500,
            textAlign = TextAlign.Center,
            color = LocalPallet.current.invert.black
        )

        Row(
            modifier = Modifier.padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                modifier = Modifier.weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(LocalPallet.current.transparent.black.quaternary)
                    .padding(vertical = 18.dp),
                text = stringResource(Res.string.device_btn_mute),
                fontSize = 16.sp,
                fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center,
                color = LocalPallet.current.invert.black
            )
            Text(
                modifier = Modifier.weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(LocalPallet.current.transparent.black.quaternary)
                    .padding(vertical = 18.dp),
                text = stringResource(Res.string.device_btn_reboot),
                fontSize = 16.sp,
                fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center,
                color = LocalPallet.current.invert.black
            )
            Icon(
                modifier = Modifier.weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(LocalPallet.current.transparent.black.quaternary)
                    .padding(vertical = 18.dp),
                painter = painterResource(Res.drawable.ic_settings),
                contentDescription = null,
                tint = LocalPallet.current.neutral.secondary
            )
        }
    }
}