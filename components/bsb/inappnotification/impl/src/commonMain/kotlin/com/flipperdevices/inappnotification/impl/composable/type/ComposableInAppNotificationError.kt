package com.flipperdevices.inappnotification.impl.composable.type

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import busystatusbar.components.bsb.inappnotification.impl.generated.resources.Res
import busystatusbar.components.bsb.inappnotification.impl.generated.resources.pic_update_error
import com.flipperdevices.inappnotification.api.model.InAppNotification
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun ComposableInAppNotificationError(
    title: StringResource?,
    desc: StringResource?,
    onClickAction: () -> Unit
) {
    ComposableInAppNotificationBase(
        icon = {
            Image(
                modifier = Modifier.padding(12.dp).size(24.dp),
                painter = painterResource(Res.drawable.pic_update_error),
                contentDescription = title?.let { stringResource(it) },
            )
        },
        title = title,
        desc = desc,
        actionButton = null
    )
}