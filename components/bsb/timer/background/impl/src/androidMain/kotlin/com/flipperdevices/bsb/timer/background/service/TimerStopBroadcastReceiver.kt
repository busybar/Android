package com.flipperdevices.bsb.timer.background.service

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build

class TimerStopBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val stopIntent = Intent(context, TimerForegroundService::class.java).apply {
            action = TimerServiceActionEnum.STOP.actionId
        }
        context.startService(stopIntent)
    }

    companion object {
        private const val ACTION =
            "com.flipperdevices.bsb.timer.background.service.TimerStopBroadcastReceiver"

        @SuppressLint("UnspecifiedImmutableFlag", "ObsoleteSdkInt")
        fun getStopTimerIntent(context: Context): PendingIntent {
            val intent = Intent(context, TimerStopBroadcastReceiver::class.java)
            intent.action = ACTION

            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            } else {
                PendingIntent.getBroadcast(context, 0, intent, 0)
            }
        }
    }
}