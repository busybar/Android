package com.flipperdevices.busybar.search.permissions

class BSBPermissionsController(
    val realController: dev.icerock.moko.permissions.PermissionsController
) : PermissionsController {
    override suspend fun providePermission(
        permission: Permission
    ) = realController.providePermission(toMoko(permission))

    override suspend fun isPermissionGranted(
        permission: Permission
    ) = realController.isPermissionGranted(toMoko(permission))

    override fun openAppSettings() = realController.openAppSettings()
}