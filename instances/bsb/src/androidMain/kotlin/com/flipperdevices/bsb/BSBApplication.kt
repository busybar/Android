package com.flipperdevices.bsb

import android.app.Application
import com.flipperdevices.bsb.di.AndroidAppComponent
import com.flipperdevices.bsb.di.create
import com.flipperdevices.core.activityholder.CurrentActivityHolder
import com.flipperdevices.core.ktx.jre.FlipperDispatchers
import com.russhwolf.settings.SharedPreferencesSettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import timber.log.Timber

class BSBApplication : Application() {
    private val settings by lazy {
        SharedPreferencesSettings(
            baseContext.getSharedPreferences(
                "settings",
                MODE_PRIVATE
            )
        )
    }
    private val applicationScope = CoroutineScope(
        SupervisorJob() + FlipperDispatchers.default
    )

    val appComponent by lazy {
        AndroidAppComponent::class.create(
            settings,
            applicationScope,
            this@BSBApplication
        )
    }

    override fun onCreate() {
        super.onCreate()

        CurrentActivityHolder.register(this)

        Timber.plant(Timber.DebugTree())
    }
}