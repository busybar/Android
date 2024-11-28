package com.flipperdevices.bsb.timer.background.service

import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.lifecycle.LifecycleService
import com.flipperdevices.bsb.timer.background.api.TimerApi
import com.flipperdevices.bsb.timer.background.di.ServiceDIComponent
import com.flipperdevices.bsb.timer.background.model.TimerAction
import com.flipperdevices.bsb.timer.background.model.TimerServiceActionEnum
import com.flipperdevices.bsb.timer.background.model.TimerState
import com.flipperdevices.core.di.ComponentHolder
import com.flipperdevices.core.ktx.android.toFullString
import com.flipperdevices.core.log.LogTagProvider
import com.flipperdevices.core.log.error
import com.flipperdevices.core.log.info
import kotlinx.serialization.json.Json

internal const val EXTRA_KEY_TIMER_STATE = "timer_state"
internal const val EXTRA_KEY_TIMER_ACTION = "timer_action"


class TimerForegroundService : LifecycleService(), LogTagProvider {
    override val TAG = "TimerForegroundService"

    private val serviceDIComponent = ComponentHolder.component<ServiceDIComponent>()
    private val delegate = serviceDIComponent.commonTimerApi

    private val binder = TimerServiceBinder(delegate)

    override fun onCreate() {
        info { "onCreate" }
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        info { "Receive command ${intent?.toFullString()}" }
        super.onStartCommand(intent, flags, startId)

        val serviceAction = intent?.action
        if (serviceAction != null) {
            when (serviceAction) {
                TimerServiceActionEnum.START.actionId -> {
                    val timerStateString = intent.getStringExtra(EXTRA_KEY_TIMER_STATE)
                    if (timerStateString != null) {
                        val timerState = Json.decodeFromString<TimerState>(timerStateString)
                        delegate.startTimer(timerState)
                    } else {
                        error { "Not found timer start" }
                    }
                }

                TimerServiceActionEnum.STOP.actionId -> {
                    delegate.stopTimer()
                    stopServiceInternal()
                }

                TimerServiceActionEnum.ACTION.actionId -> {
                    if (intent.hasExtra(EXTRA_KEY_TIMER_ACTION)) {
                        val timerActionNumber = intent.getIntExtra(EXTRA_KEY_TIMER_ACTION, 0)
                        val timerAction = TimerAction.entries[timerActionNumber]
                        delegate.onAction(timerAction)
                    } else {
                        error { "Not found timer action" }
                    }
                }
            }
        } else {
            error { "Receive intent without action" }
        }

        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder {
        info { "On bind ${intent.toFullString()}" }
        super.onBind(intent)
        return binder
    }

    private fun stopServiceInternal() {
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }
}

class TimerServiceBinder(
    val timerApi: TimerApi
) : Binder()