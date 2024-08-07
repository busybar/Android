package com.flipperdevices.busybar.device.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flipperdevices.busybar.apps.composable.apps.BusyBarApp

@Composable
fun DeviceScreenComposable(
    modifier: Modifier,
    busyBarApp: BusyBarApp,
    onChangeApp: () -> Unit,
    onClick: () -> Unit
) = Column(
    modifier = modifier.padding(horizontal = 14.dp)
        .verticalScroll(rememberScrollState())
) {
    BarHeaderComposable(
        Modifier.padding(
            top = 26.dp,
        ),
        busyBarApp,
        onChangeApp = onChangeApp
    )

    ControlButtonsComposable(
        modifier = Modifier.padding(top = 24.dp),
        onClick = onClick
    )

    RemoteButtonsComposable(
        modifier = Modifier.padding(top = 40.dp),
        onClick = onClick
    )

    AboutDeviceComposable(
        modifier = Modifier.padding(top = 40.dp, bottom = 26.dp),
        onClick = onClick
    )
}