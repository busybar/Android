package com.flipperdevices.bsb.timer.main.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busystatusbar.components.bsb.timer.main.impl.generated.resources.Res
import busystatusbar.components.bsb.timer.main.impl.generated.resources.ic_pause
import busystatusbar.components.bsb.timer.main.impl.generated.resources.ic_play
import com.flipperdevices.bsb.core.theme.LocalBusyBarFonts
import com.flipperdevices.bsb.core.theme.LocalPallet
import com.flipperdevices.bsb.timer.background.model.TimerAction
import com.flipperdevices.core.ktx.jre.clickableRipple
import org.jetbrains.compose.resources.painterResource

@Composable
fun TimerControlPanelComposable(
    modifier: Modifier,
    isOnPause: Boolean,
    onAction: (TimerAction) -> Unit
) {
    Row(
        modifier,
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ControlElementComposable(
            text = "-5",
            onClick = { onAction(TimerAction.MINUS) }
        )
        Icon(
            modifier = Modifier
                .clip(CircleShape)
                .border(2.dp, LocalPallet.current.black.invert, CircleShape)
                .clickableRipple { onAction(TimerAction.PAUSE) }
                .padding(33.dp)
                .size(34.dp),
            painter = painterResource(
                if (isOnPause) {
                    Res.drawable.ic_play
                } else {
                    Res.drawable.ic_pause
                }
            ),
            contentDescription = null,
            tint = LocalPallet.current.black.invert
        )
        ControlElementComposable(
            text = "+5",
            onClick = { onAction(TimerAction.PLUS) }
        )
    }
}

@Composable
private fun ControlElementComposable(
    text: String,
    onClick: () -> Unit
) {
    val localDensity = LocalDensity.current
    var width by remember { mutableStateOf<Dp?>(null) }
    var modifier: Modifier = Modifier
    width?.let {
        modifier = modifier.size(it)
    }
    Box(modifier
        .clip(CircleShape)
        .border(2.dp, LocalPallet.current.black.invert, CircleShape)
        .onGloballyPositioned {
            width = with(localDensity) { it.size.width.toDp() }
        }
        .clickableRipple(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .padding(22.dp),
            text = text,
            fontFamily = LocalBusyBarFonts.current.pragmatica,
            fontWeight = FontWeight.W400,
            color = LocalPallet.current.black.invert,
            fontSize = 24.sp
        )
    }
}