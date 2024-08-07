package com.flipperdevices.busybar.search.permissions

import androidx.compose.runtime.Composable

@Composable
actual fun rememberPermissionsControllerFactory(): PermissionsControllerFactory {
    val realPermissionsFactory =
        dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory()
    return PermissionsControllerFactory {
        BSBPermissionsController(realPermissionsFactory.createPermissionsController())
    }
}
