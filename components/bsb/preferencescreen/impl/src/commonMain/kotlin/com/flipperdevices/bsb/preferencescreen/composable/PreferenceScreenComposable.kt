package com.flipperdevices.bsb.preferencescreen.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
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
import busystatusbar.components.bsb.core.res.generated.resources.ic_close
import busystatusbar.components.bsb.preferencescreen.impl.generated.resources.Res
import busystatusbar.components.bsb.preferencescreen.impl.generated.resources.preference_title
import busystatusbar.components.bsb.core.res.generated.resources.Res as CommonRes
import com.flipperdevices.bsb.core.theme.LocalBusyBarFonts
import com.flipperdevices.bsb.core.theme.LocalPallet
import com.flipperdevices.bsb.preferencescreen.model.PreferenceScreenState
import com.flipperdevices.core.ktx.jre.clickableRipple
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun PreferenceScreenComposable(
    modifier: Modifier,
    onBack: () -> Unit,
    screenState: PreferenceScreenState,
    onRequestDND: (Boolean) -> Unit,
    onAppBlock: (Boolean) -> Unit
) {
    Column(
        modifier
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomEnd = 16.dp,
                        bottomStart = 16.dp
                    )
                )
                .background(LocalPallet.current.surface.primary)
                .statusBarsPadding()
                .padding(top = 16.dp)
        )
        Column(
            Modifier
                .padding(top = 2.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomEnd = 0.dp,
                        bottomStart = 0.dp
                    )
                )
                .background(LocalPallet.current.surface.primary)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 24.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(Res.string.preference_title),
                    fontSize = 36.sp,
                    fontFamily = LocalBusyBarFonts.current.pragmatica,
                    fontWeight = FontWeight.W400,
                    color = LocalPallet.current.black.invert,
                )

                Icon(
                    modifier = Modifier.size(24.dp)
                        .clickableRipple(bounded = false, onClick = onBack),
                    painter = painterResource(CommonRes.drawable.ic_close),
                    contentDescription = null,
                    tint = LocalPallet.current.black.invert,
                )
            }

            SettingsColumnComposable(
                modifier = Modifier
                    .padding(vertical = 24.dp, horizontal = 16.dp)
                    .weight(1f),
                screenState = screenState,
                onRequestDND = onRequestDND,
                onAppBlock = onAppBlock
            )
        }
    }

}