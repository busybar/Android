package com.flipperdevices.bsb.timer.active.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import busystatusbar.components.bsb.timer.active.impl.generated.resources.Res
import busystatusbar.components.bsb.timer.active.impl.generated.resources.ic_stop
import busystatusbar.components.bsb.timer.active.impl.generated.resources.timer_active_stop
import com.flipperdevices.bsb.core.theme.LocalPallet
import com.flipperdevices.bsb.timer.common.composable.BusyButtonComposable

@Composable
fun StopButtonComposable(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BusyButtonComposable(
        modifier = modifier,
        buttonColor = LocalPallet.current.white.onColor,
        icon = Res.drawable.ic_stop,
        text = Res.string.timer_active_stop,
        onClick = onClick,
        secondColor = LocalPallet.current.black.onColor
    )
}
