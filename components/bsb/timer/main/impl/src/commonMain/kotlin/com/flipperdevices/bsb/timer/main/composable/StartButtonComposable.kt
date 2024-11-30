package com.flipperdevices.bsb.timer.main.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import busystatusbar.components.bsb.timer.common.generated.resources.ic_play
import busystatusbar.components.bsb.timer.main.impl.generated.resources.Res
import busystatusbar.components.bsb.timer.main.impl.generated.resources.timer_main_busy
import busystatusbar.components.bsb.timer.common.generated.resources.Res as CommonRes
import com.flipperdevices.bsb.core.theme.LocalPallet
import com.flipperdevices.bsb.timer.common.composable.BusyButtonComposable

@Composable
fun StartButtonComposable(
    modifier: Modifier,
    onClick: () -> Unit
) {
    BusyButtonComposable(
        modifier = modifier,
        buttonColor = Color(0xFFF42323),
        icon = CommonRes.drawable.ic_play,
        text = Res.string.timer_main_busy,
        onClick = onClick,
        secondColor = LocalPallet.current.white.onColor
    )
}