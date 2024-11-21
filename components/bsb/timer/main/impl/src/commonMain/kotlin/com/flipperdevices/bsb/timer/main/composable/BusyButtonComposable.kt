package com.flipperdevices.bsb.timer.main.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busystatusbar.components.bsb.timer.main.impl.generated.resources.Res
import busystatusbar.components.bsb.timer.main.impl.generated.resources.ic_play
import busystatusbar.components.bsb.timer.main.impl.generated.resources.timer_main_busy
import com.flipperdevices.bsb.core.theme.LocalBusyBarFonts
import com.flipperdevices.bsb.core.theme.LocalPallet
import com.flipperdevices.core.ktx.jre.clickableRipple
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun BusyButtonComposable(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier
            .clip(RoundedCornerShape(16.dp))
            .background(LocalPallet.current.black.invert)
            .padding(bottom = 24.dp)
            .border(
                width = 2.dp,
                color = LocalPallet.current.black.invert,
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                Color(0xFFF42323),
                shape = RoundedCornerShape(16.dp)
            )
            .clickableRipple(onClick = onClick)
            .padding(
                vertical = 24.dp,
                horizontal = 32.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(34.dp),
            painter = painterResource(Res.drawable.ic_play),
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(start = 12.dp),
            text = stringResource(Res.string.timer_main_busy),
            style = LocalTextStyle.current.merge(
                TextStyle(
                    fontSize = 48.sp,
                    fontFamily = LocalBusyBarFonts.current.pragmatica,
                    fontWeight = FontWeight.W500,
                    color = LocalPallet.current.black.invert,
                    lineHeight = 48.sp,
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Center,
                        trim = LineHeightStyle.Trim.Both
                    ),
                    textAlign = TextAlign.Center
                )
            ),
        )
    }
}