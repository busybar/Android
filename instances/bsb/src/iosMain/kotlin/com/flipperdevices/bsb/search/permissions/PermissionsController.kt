package com.flipperdevices.bsb.search.permissions

actual interface PermissionsController {
    /**
     * Check is permission already granted and if not - request permission from user.
     *
     * @param permission what permission we want to provide
     *
     * @throws DeniedException if user decline request, but we can retry (only on Android)
     * @throws DeniedAlwaysException if user decline request and we can't show request again
     *  (we should send user to settings)
     * @throws RequestCanceledException if user cancel request without response (only on Android)
     */
    actual suspend fun providePermission(permission: Permission)

    /**
     * @return true if permission already granted. In all other cases - false.
     */
    actual suspend fun isPermissionGranted(permission: Permission): Boolean

    /**
     * Open system UI of application settings to change permissions state
     */
    actual fun openAppSettings()
}
