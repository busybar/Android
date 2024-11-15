package com.flipperdevices.busybar

import android.app.Application
import timber.log.Timber

class BSBApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}