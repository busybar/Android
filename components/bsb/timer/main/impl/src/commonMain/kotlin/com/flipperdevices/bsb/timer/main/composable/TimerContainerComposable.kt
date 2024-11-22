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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busystatusbar.components.bsb.timer.main.impl.generated.resources.Res
import busystatusbar.components.bsb.timer.main.impl.generated.resources.ic_play
import busystatusbar.components.bsb.timer.main.impl.generated.resources.timer_main_stop_active
import com.flipperdevices.bsb.core.theme.LocalBusyBarFonts
import com.flipperdevices.bsb.core.theme.LocalPallet
import com.flipperdevices.bsb.timer.setup.model.TimerState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TimerContainerComposable(
    modifier: Modifier,
    timerState: TimerState
) {
    Column(
        modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(24.dp)
                    .padding(end = 12.dp),
                painter = painterResource(Res.drawable.ic_play),
                contentDescription = null
            )
            Text(
                text = stringResource(Res.string.timer_main_stop_active),
                fontSize = 32.sp,
                color = LocalPallet.current.black.invert,
                fontWeight = FontWeight.W500,
                fontFamily = LocalBusyBarFonts.current.pragmatica
            )
        }
        TimerTimeComposable(
            modifier = Modifier.padding(top = 23.dp, bottom = 25.dp),
            timerState = timerState
        )
        TimerControlPanelComposable(
            modifier = Modifier
        )
    }
}