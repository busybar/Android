package com.flipperdevices.bsb.search.permissions

import androidx.compose.runtime.Composable

@Composable
actual fun BindEffect(permissionsController: PermissionsController) {
    dev.icerock.moko.permissions.compose.BindEffect(
        permissionsController = (permissionsController as BSBPermissionsController).realController
    )
}
