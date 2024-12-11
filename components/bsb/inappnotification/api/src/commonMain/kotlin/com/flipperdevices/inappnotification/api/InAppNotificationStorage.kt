package com.flipperdevices.inappnotification.api

import com.flipperdevices.inappnotification.api.model.InAppNotification

interface InAppNotificationStorage {
    suspend fun subscribe(listener: InAppNotificationListener)
    suspend fun unsubscribe()
    suspend fun addNotification(notification: InAppNotification)
}
