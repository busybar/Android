package com.flipperdevices.busybar.search.permissions

import androidx.compose.runtime.Composable


fun interface PermissionsControllerFactory {
    fun createPermissionsController(): PermissionsController
}

@Composable
expect fun rememberPermissionsControllerFactory(): PermissionsControllerFactory
