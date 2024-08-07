package com.flipperdevices.busybar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import com.flipperdevices.busybar.di.createAppComponent
import com.russhwolf.settings.SharedPreferencesSettings
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory

class MainActivity : ComponentActivity() {
    private val settings by lazy {
        SharedPreferencesSettings(
            baseContext.getSharedPreferences(
                "settings",
                MODE_PRIVATE
            )
        )
    }
    private val appComponent by lazy { createAppComponent(settings) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            return
        }

        enableEdgeToEdge()

        val rootComponent = appComponent.rootComponent(
            defaultComponentContext()
        )

        setContent {
            App(rootComponent)
        }
    }
}