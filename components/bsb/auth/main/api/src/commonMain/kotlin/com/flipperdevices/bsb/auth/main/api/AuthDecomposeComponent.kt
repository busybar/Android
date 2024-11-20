package com.flipperdevices.bsb.auth.main.api

import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.bsb.auth.main.model.AuthRootNavigationConfig
import com.flipperdevices.ui.decompose.CompositeDecomposeComponent

abstract class AuthDecomposeComponent : CompositeDecomposeComponent<AuthRootNavigationConfig>() {
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext
        ): AuthDecomposeComponent
    }
}