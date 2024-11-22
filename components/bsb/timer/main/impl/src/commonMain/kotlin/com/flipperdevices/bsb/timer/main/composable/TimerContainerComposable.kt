package com.flipperdevices.bsb.timer.main.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.constraintlayout.compose.ConstraintLayout
import busystatusbar.components.bsb.timer.main.impl.generated.resources.Res
import busystatusbar.components.bsb.timer.main.impl.generated.resources.ic_play
import busystatusbar.components.bsb.timer.main.impl.generated.resources.timer_main_stop_active
import com.flipperdevices.bsb.core.theme.LocalBusyBarFonts
import com.flipperdevices.bsb.core.theme.LocalPallet
import com.flipperdevices.bsb.timer.main.model.TimerAction
import com.flipperdevices.bsb.timer.main.model.TimerControlledState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TimerContainerComposable(
    modifier: Modifier,
    timerState: TimerControlledState,
    onAction: (TimerAction) -> Unit
) {
    ConstraintLayout(
        modifier.fillMaxWidth()
    ) {
        val (text, time, control) = createRefs()
        TimerActiveTextComposable(
            modifier = Modifier.constrainAs(text) {
                bottom.linkTo(time.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        TimerTimeComposable(
            modifier = Modifier.padding(top = 23.dp, bottom = 25.dp)
                .constrainAs(time) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            timerState = timerState.timerState
        )
        TimerControlPanelComposable(
            modifier = Modifier.constrainAs(control) {
                top.linkTo(time.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            isOnPause = timerState.pauseOn != null,
            onAction = onAction
        )
    }
}

@Composable
private fun TimerActiveTextComposable(modifier: Modifier) {
    Row(
        modifier = modifier.padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .padding(end = 12.dp)
                .size(24.dp),
            painter = painterResource(Res.drawable.ic_play),
            contentDescription = null,
            tint = LocalPallet.current.black.invert
        )
        Text(
            text = stringResource(Res.string.timer_main_stop_active),
            fontSize = 32.sp,
            color = LocalPallet.current.black.invert,
            fontWeight = FontWeight.W500,
            fontFamily = LocalBusyBarFonts.current.pragmatica
        )
    }
}