package com.flipperdevices.inappnotification.impl.composable.type

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flipperdevices.bsb.core.theme.LocalBusyBarFonts
import com.flipperdevices.bsb.core.theme.LocalPallet
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun ComposableInAppNotificationBase(
    icon: (@Composable () -> Unit)?,
    title: StringResource?,
    desc: StringResource?,
    actionButton: (@Composable () -> Unit)?,
    modifier: Modifier = Modifier
) {
    ComposableInAppNotificationBase(
        icon = icon,
        title = title?.let { stringResource(it) },
        desc = desc?.let { stringResource(it) },
        actionButton = actionButton,
        modifier = modifier
    )
}

@Composable
internal fun ComposableInAppNotificationBase(
    icon: (@Composable () -> Unit)?,
    title: String?,
    desc: String?,
    actionButton: (@Composable () -> Unit)?,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 8.dp,
                horizontal = 12.dp
            ),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon?.invoke()
        Column(
            Modifier
                .weight(1f)
        ) {
            if (title != null) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
                    fontWeight = FontWeight.W500,
                    color = LocalPallet.current.black.invert
                )
            }
            if (desc != null) {
                Text(
                    text = desc,
                    fontSize = 14.sp,
                    fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
                    fontWeight = FontWeight.W400,
                    color = LocalPallet.current.black.invert
                )
            }
        }
        actionButton?.invoke()
    }
}
