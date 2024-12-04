package com.flipperdevices.bsb.preferencescreen.composable.debug

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busystatusbar.components.bsb.preferencescreen.impl.generated.resources.Res
import busystatusbar.components.bsb.preferencescreen.impl.generated.resources.preference_auth
import busystatusbar.components.bsb.preferencescreen.impl.generated.resources.preference_auth_desc
import com.flipperdevices.bsb.cloud.model.BSBUser
import com.flipperdevices.bsb.core.theme.LocalBusyBarFonts
import com.flipperdevices.bsb.core.theme.LocalPallet
import com.flipperdevices.core.ktx.jre.clickableRipple
import org.jetbrains.compose.resources.stringResource

@Composable
fun AuthComposable(
    modifier: Modifier,
    bsbUser: BSBUser?,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clickableRipple(onClick = onClick)
            .clip(RoundedCornerShape(8.dp))
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
                text = stringResource(Res.string.preference_auth),
                fontFamily = LocalBusyBarFonts.current.pragmatica,
                fontWeight = FontWeight.W500,
                color = LocalPallet.current.black.invert,
                fontSize = 22.sp
            )
        }

        if (bsbUser != null) {
            Text(
                text = stringResource(Res.string.preference_auth_desc, bsbUser.email),
                fontFamily = LocalBusyBarFonts.current.pragmatica,
                fontWeight = FontWeight.W500,
                color = LocalPallet.current.neutral.tertiary,
                fontSize = 16.sp
            )
        }
    }
}
