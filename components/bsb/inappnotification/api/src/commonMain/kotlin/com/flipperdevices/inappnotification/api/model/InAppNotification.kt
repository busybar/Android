package com.flipperdevices.inappnotification.api.model

import androidx.compose.runtime.Immutable
import org.jetbrains.compose.resources.StringResource
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

private val NOTIFICATION_DURATION = 5.seconds

@Immutable
sealed class InAppNotification {
    abstract val duration: Duration

    data class Error(
        val title: StringResource?,
        val desc: StringResource?,
        override val duration: Duration = NOTIFICATION_DURATION
    ) : InAppNotification()

    data class ErrorEmailSend(
        override val duration: Duration = NOTIFICATION_DURATION
    ) : InAppNotification()
}
