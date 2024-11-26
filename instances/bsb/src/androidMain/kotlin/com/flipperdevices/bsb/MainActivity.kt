package com.flipperdevices.bsb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import com.flipperdevices.bsb.di.AndroidAppComponent
import com.flipperdevices.bsb.di.create
import com.russhwolf.settings.SharedPreferencesSettings

class MainActivity : ComponentActivity() {
    private val settings by lazy {
        SharedPreferencesSettings(
            baseContext.getSharedPreferences(
                "settings",
                MODE_PRIVATE
            )
        )
    }
    private val appComponent by lazy {
        AndroidAppComponent::class.create(settings)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            return
        }

        enableEdgeToEdge()

        val rootComponent = appComponent.rootDecomposeComponentFactory(
            defaultComponentContext()
        )

        setContent {
            App(rootComponent, appComponent)
        }
    }
}