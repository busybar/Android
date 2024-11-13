package com.flipperdevices.busybar.device.composable.login

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LogInBlockComposable(
    modifier: Modifier,
    email: String?,
    onOpenLogin: () -> Unit,
    onLogout: () -> Unit
) {
    if (email == null) {
        LogInCloudComposable(modifier, onOpenLogin)
    } else {
        LogInRowComposable(modifier.fillMaxWidth(), email, onLogout)
    }
}