package com.flipperdevices.inappnotification.impl.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flipperdevices.bsb.core.theme.LocalPallet
import com.flipperdevices.inappnotification.api.model.InAppNotification
import com.flipperdevices.inappnotification.impl.composable.type.ComposableInAppNotificationError
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.time.Duration.Companion.seconds

val VISIBLE_ANIMATION = 1.seconds
private val VISIBLE_ANIMATION_MS = VISIBLE_ANIMATION.inWholeMilliseconds.toInt()

@Composable
fun ComposableInAppNotification(
    notification: InAppNotification,
    onNotificationHide: (InAppNotification) -> Unit,
    modifier: Modifier = Modifier
) {
    ComposableInAppNotificationCard(
        notification = notification,
        onNotificationHide = onNotificationHide,
        modifier = modifier
    )
}

@Composable
@Suppress("LongMethod")
private fun ComposableInAppNotificationCard(
    notification: InAppNotification,
    onNotificationHide: (InAppNotification) -> Unit,
    modifier: Modifier = Modifier
) {
    var visibleState by remember { mutableStateOf(false) }
    var actionClick by remember { mutableStateOf(false) }
    LaunchedEffect(notification) {
        visibleState = true
    }

    val onClickAction = remember(onNotificationHide) {
        {
            visibleState = false
            actionClick = true
            onNotificationHide(notification)
        }
    }
    AnimatedVisibility(
        modifier = modifier,
        visible = visibleState,
        enter = fadeIn(animationSpec = tween(VISIBLE_ANIMATION_MS)),
        exit = fadeOut(animationSpec = tween(VISIBLE_ANIMATION_MS))
    ) {
        Card(
            modifier = Modifier
                .padding(all = 14.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(size = 8.dp),
            backgroundColor = LocalPallet.current.neutral.quinary
        ) {
            when (notification) {
                is InAppNotification.Error -> ComposableInAppNotificationError(
                    notification,
                    onClickAction = onClickAction
                )
            }
        }
    }

    val scope = rememberCoroutineScope()
    DisposableEffect(notification, onNotificationHide) {
        val fadeOutJob = scope.launch {
            delay(max(0L, notification.duration.inWholeMilliseconds - VISIBLE_ANIMATION_MS))
            visibleState = false
        }

        val hiddenJob = scope.launch {
            delay(notification.duration.inWholeMilliseconds)
            if (!actionClick) {
                onNotificationHide(notification)
            }
        }
        onDispose {
            fadeOutJob.cancel()
            hiddenJob.cancel()
        }
    }
}
