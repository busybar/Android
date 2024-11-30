package com.flipperdevices.bsb.preferencescreen.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flipperdevices.bsb.core.theme.LocalBusyBarFonts
import com.flipperdevices.bsb.core.theme.LocalPallet
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SettingItemComposable(
    modifier: Modifier,
    title: StringResource,
    description: StringResource,
    enabled: Boolean,
    onSwitch: (Boolean) -> Unit
) {
    Column(
        modifier = modifier.clip(RoundedCornerShape(8.dp))
            .background(LocalPallet.current.transparent.blackInvert.quaternary)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(title),
                fontFamily = LocalBusyBarFonts.current.pragmatica,
                fontWeight = FontWeight.W500,
                color = LocalPallet.current.black.invert,
                fontSize = 22.sp
            )
            Switch(
                checked = enabled,
                onCheckedChange = onSwitch
            )
        }

        Text(
            text = stringResource(description),
            fontFamily = LocalBusyBarFonts.current.pragmatica,
            fontWeight = FontWeight.W500,
            color = LocalPallet.current.neutral.tertiary,
            fontSize = 16.sp
        )
    }
}