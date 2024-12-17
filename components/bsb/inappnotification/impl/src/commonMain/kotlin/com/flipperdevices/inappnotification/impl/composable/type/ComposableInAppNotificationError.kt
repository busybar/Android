package com.flipperdevices.inappnotification.impl.composable.type

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import busystatusbar.components.bsb.inappnotification.impl.generated.resources.Res
import busystatusbar.components.bsb.inappnotification.impl.generated.resources.pic_update_error
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun ComposableInAppNotificationError(
    title: StringResource?,
    desc: StringResource?,
    @Suppress("UnusedParameter") onClickAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ComposableInAppNotificationBase(
        modifier = modifier,
        icon = {
            Image(
                modifier = Modifier.padding(
                    end = 8.dp,
                ).size(32.dp),
                painter = painterResource(Res.drawable.pic_update_error),
                contentDescription = title?.let { stringResource(it) },
            )
        },
        title = title,
        desc = desc,
        actionButton = null,
    )
}
