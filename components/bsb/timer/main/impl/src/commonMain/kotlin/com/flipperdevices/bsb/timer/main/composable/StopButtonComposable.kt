package com.flipperdevices.bsb.timer.main.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import busystatusbar.components.bsb.timer.main.impl.generated.resources.Res
import busystatusbar.components.bsb.timer.main.impl.generated.resources.ic_stop
import busystatusbar.components.bsb.timer.main.impl.generated.resources.timer_main_stop
import com.flipperdevices.bsb.core.theme.LocalPallet

@Composable
fun StopButtonComposable(
    modifier: Modifier,
    onClick: () -> Unit
) {
    BusyButtonComposable(
        modifier = modifier,
        buttonColor = LocalPallet.current.white.onColor,
        icon = Res.drawable.ic_stop,
        text = Res.string.timer_main_stop,
        onClick = onClick
    )
}