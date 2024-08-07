package com.flipperdevices.busybar.device.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.device_section_about_dev_mode_title
import busystatusbar.composeapp.generated.resources.device_section_about_dev_mode_value
import busystatusbar.composeapp.generated.resources.device_section_about_version_title
import busystatusbar.composeapp.generated.resources.device_section_about_version_value
import com.flipperdevices.busybar.core.theme.LocalBusyBarFonts
import com.flipperdevices.busybar.core.theme.LocalPallet
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AboutDeviceRowsComposable(
    modifier: Modifier
) {
    val pairs = listOf(
        Res.string.device_section_about_version_title to Res.string.device_section_about_version_value,
        Res.string.device_section_about_dev_mode_title to Res.string.device_section_about_dev_mode_value
    )

    Column(
        modifier = modifier.padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        pairs.forEachIndexed { index, (key, value) ->
            AboutDeviceRowComposable(key, value)
            if (index != pairs.lastIndex) {
                Box(
                    Modifier.fillMaxWidth()
                        .height(1.dp)
                        .background(LocalPallet.current.neutral.quaternary)
                )
            }
        }
    }

}

@Composable
private fun AboutDeviceRowComposable(
    key: StringResource,
    value: StringResource
) {
    Row(
        Modifier.padding(vertical = 12.dp)
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(key),
            fontSize = 16.sp,
            fontFamily = LocalBusyBarFonts.current.jetbrainsMono,
            fontWeight = FontWeight.W400,
            color = LocalPallet.current.invert.black,
            letterSpacing = 0.48.sp,
        )
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(value),
            fontSize = 16.sp,
            fontFamily = LocalBusyBarFonts.current.jetbrainsMono,
            fontWeight = FontWeight.W400,
            color = LocalPallet.current.neutral.secondary,
            textAlign = TextAlign.Right,
        )
    }
}