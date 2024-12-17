package com.flipperdevices.bsb.timer.main.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import busystatusbar.components.bsb.timer.common.generated.resources.ic_play
import busystatusbar.components.bsb.timer.main.impl.generated.resources.Res
import busystatusbar.components.bsb.timer.main.impl.generated.resources.timer_main_busy
import com.flipperdevices.bsb.core.theme.LocalPallet
import com.flipperdevices.bsb.timer.common.composable.BusyButtonComposable
import busystatusbar.components.bsb.timer.common.generated.resources.Res as CommonRes

@Composable
fun StartButtonComposable(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BusyButtonComposable(
        modifier = modifier,
        buttonColor = Color(color = 0xFFF42323),
        icon = CommonRes.drawable.ic_play,
        text = Res.string.timer_main_busy,
        onClick = onClick,
        secondColor = LocalPallet.current.white.onColor
    )
}
