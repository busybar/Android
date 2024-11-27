package com.flipperdevices.bsb.timer.background.service

import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.lifecycle.LifecycleService
import com.flipperdevices.bsb.timer.background.api.TimerApi
import com.flipperdevices.bsb.timer.background.di.ServiceDIComponent
import com.flipperdevices.core.di.ComponentHolder
import com.flipperdevices.core.log.info

class TimerForegroundService : LifecycleService() {
    private val serviceDIComponent = ComponentHolder.component<ServiceDIComponent>()

    private val binder = TimerServiceBinder(serviceDIComponent.timerApi)

    override fun onCreate() {
        super.onCreate()

    }

    override fun onBind(intent: Intent): IBinder {
        super.onBind(intent)
        info { "On bind $intent" }
        return binder
    }
}

class TimerServiceBinder(
    val timerApi: TimerApi
) : Binder()