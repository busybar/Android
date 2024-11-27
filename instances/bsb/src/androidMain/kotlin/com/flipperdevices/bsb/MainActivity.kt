package com.flipperdevices.bsb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            return
        }

        enableEdgeToEdge()

        val appComponent = (application as BSBApplication).appComponent

        val rootComponent = appComponent.rootDecomposeComponentFactory(
            defaultComponentContext()
        )

        setContent {
            App(rootComponent, appComponent)
        }
    }
}