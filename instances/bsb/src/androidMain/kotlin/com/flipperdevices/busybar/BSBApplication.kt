package com.flipperdevices.busybar

import android.app.Application
import com.flipperdevices.core.activityholder.CurrentActivityHolder
import timber.log.Timber

class BSBApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        CurrentActivityHolder.register(this)

        Timber.plant(Timber.DebugTree())
    }
}