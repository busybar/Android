package com.flipperdevices.busybar.device.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flipperdevices.busybar.apps.composable.apps.BusyBarApp
import com.flipperdevices.busybar.device.composable.login.LogInBlockComposable

@Composable
fun DeviceScreenComposable(
    modifier: Modifier,
    busyBarApp: BusyBarApp,
    onChangeApp: () -> Unit,
    onOpenSettings: () -> Unit,
    onClick: () -> Unit,
    devMode: Boolean,
    email: String?,
    onOpenLogin: () -> Unit,
    onLogout: () -> Unit
) = Column(
    modifier = modifier.padding(horizontal = 14.dp)
        .verticalScroll(rememberScrollState())
) {
    LogInBlockComposable(
        Modifier.padding(top = 8.dp),
        email = email,
        onOpenLogin = onOpenLogin,
        onLogout = onLogout
    )

    BarHeaderComposable(
        Modifier.padding(
            top = 32.dp,
        ),
        busyBarApp,
        onChangeApp = onChangeApp
    )

    ControlButtonsComposable(
        modifier = Modifier.padding(top = 24.dp),
        onClick = onClick,
        onOpenSettings = onOpenSettings
    )

    RemoteButtonsComposable(
        modifier = Modifier.padding(top = 40.dp),
        onClick = onClick
    )

    AboutDeviceComposable(
        modifier = Modifier.padding(top = 40.dp, bottom = 26.dp),
        onClick = onClick,
        devMode = devMode
    )
}