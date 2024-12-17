package com.flipperdevices.bsb.timer.background.service

import android.app.Notification
import android.content.Context
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.flipperdevices.bsb.timer.background.impl.R
import com.flipperdevices.bsb.timer.background.model.ControlledTimerState

private const val TIMER_NOTIFICATION_CHANNEL = "timer_notification_channel"

object NotificationTimerBuilder {
    fun buildNotification(context: Context, timer: ControlledTimerState? = null): Notification {
        createChannelIfNotYet(context)

        val message = if (timer == null) {
            context.getString(R.string.timer_notification_desc_empty)
        } else {
            timer.timerState.toHumanReadableString() + if (timer.isOnPause) {
                " (Paused)"
            } else {
                ""
            }
        }

        return NotificationCompat.Builder(context, TIMER_NOTIFICATION_CHANNEL)
            .setContentTitle(context.getString(R.string.timer_notification_title))
            .setContentText(message)
            .setSilent(true)
            .setSmallIcon(R.drawable.ic_close_icon)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(
                R.drawable.ic_close_icon,
                context.getString(R.string.timer_notification_stop_btn),
                TimerStopBroadcastReceiver.getStopTimerIntent(context)
            ).build()
    }

    private fun createChannelIfNotYet(context: Context) {
        val notificationManager = NotificationManagerCompat.from(context)

        val flipperChannel = NotificationChannelCompat.Builder(
            TIMER_NOTIFICATION_CHANNEL,
            NotificationManagerCompat.IMPORTANCE_LOW
        ).setName(context.getString(R.string.timer_notification_channel_title))
            .setDescription(context.getString(R.string.timer_notification_channel_desc))
            .build()

        notificationManager.createNotificationChannel(flipperChannel)
    }
}
