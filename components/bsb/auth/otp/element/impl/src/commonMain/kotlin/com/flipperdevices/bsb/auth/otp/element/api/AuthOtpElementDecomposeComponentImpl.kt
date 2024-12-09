package com.flipperdevices.bsb.auth.otp.element.api

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.bsb.auth.otp.element.composable.OtpRowComposable
import com.flipperdevices.bsb.auth.otp.element.api.AuthOtpElementDecomposeComponent
import com.flipperdevices.bsb.auth.otp.element.viewmodel.OtpRowViewModel
import com.flipperdevices.core.di.AppGraph
import com.flipperdevices.core.ui.lifecycle.viewModelWithFactoryWithoutRemember
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@Inject
class AuthOtpElementDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
    otpRowViewModelFactory: () -> OtpRowViewModel
) : AuthOtpElementDecomposeComponent(componentContext) {
    private val viewModel = viewModelWithFactoryWithoutRemember(null) {
        otpRowViewModelFactory()
    }

    @Composable
    override fun Render(modifier: Modifier) {
        val otpRow by viewModel.getState().collectAsState()

        Box(
            modifier.fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            OtpRowComposable(
                modifier = Modifier.fillMaxWidth(),
                onInput = viewModel::onChange,
                otpRow = otpRow
            )
        }
    }

    @Inject
    @ContributesBinding(AppGraph::class, AuthOtpElementDecomposeComponent.Factory::class)
    class Factory(
        private val factory: (
            componentContext: ComponentContext
        ) -> AuthOtpElementDecomposeComponentImpl
    ) : AuthOtpElementDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext
        ) = factory(componentContext)
    }
}