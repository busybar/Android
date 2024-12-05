package com.flipperdevices.bsb.auth.otp.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.core.di.AppGraph
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@Inject
class AuthOtpScreenDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
) : AuthOtpScreenDecomposeComponent(componentContext) {
    @Composable
    override fun Render(modifier: Modifier) {

    }

    @Inject
    @ContributesBinding(AppGraph::class, AuthOtpScreenDecomposeComponent.Factory::class)
    class Factory(
        private val factory: (
            componentContext: ComponentContext
        ) -> AuthOtpScreenDecomposeComponentImpl
    ) : AuthOtpScreenDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext
        ) = factory(componentContext)
    }
}