package com.flipperdevices.inappnotification.api

import com.flipperdevices.inappnotification.api.model.InAppNotification

fun interface InAppNotificationListener {
    suspend fun onNewNotification(notification: InAppNotification)
}
