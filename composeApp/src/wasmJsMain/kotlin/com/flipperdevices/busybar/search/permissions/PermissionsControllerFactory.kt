package com.flipperdevices.busybar.search.permissions

import androidx.compose.runtime.Composable

@Composable
actual fun rememberPermissionsControllerFactory(): PermissionsControllerFactory {
    return PermissionsControllerFactory {
        object : PermissionsController {
            override suspend fun providePermission(permission: Permission) = Unit

            override suspend fun isPermissionGranted(permission: Permission) = true

            override fun openAppSettings() = Unit
        }
    }
}
