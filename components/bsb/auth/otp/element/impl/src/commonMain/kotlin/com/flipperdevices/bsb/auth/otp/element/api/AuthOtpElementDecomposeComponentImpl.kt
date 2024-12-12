package com.flipperdevices.bsb.auth.otp.element.api

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.util.fastJoinToString
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.flipperdevices.bsb.auth.common.composable.UiConstants.ALPHA_DISABLED
import com.flipperdevices.bsb.auth.otp.element.composable.OtpRowComposable
import com.flipperdevices.bsb.auth.otp.element.model.INVISIBLE_SYMBOL
import com.flipperdevices.bsb.auth.otp.element.model.OtpElementState
import com.flipperdevices.bsb.auth.otp.element.viewmodel.OtpRowViewModel
import com.flipperdevices.core.di.AppGraph
import com.flipperdevices.core.ktx.common.FlipperDispatchers
import com.flipperdevices.core.ui.lifecycle.viewModelWithFactoryWithoutRemember
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@Inject
class AuthOtpElementDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
    otpRowViewModelFactory: () -> OtpRowViewModel
) : AuthOtpElementDecomposeComponent(componentContext) {
    private val scope = coroutineScope(FlipperDispatchers.default)

    private val viewModel = viewModelWithFactoryWithoutRemember(null) {
        otpRowViewModelFactory()
    }
    private val otpCodeState = viewModel.getState()
        .map { row ->
            row.cells.map {
                it.textFieldValue.text.trim(' ', INVISIBLE_SYMBOL)
            }.fastJoinToString("")
        }.stateIn(scope, SharingStarted.Lazily, "")


    override fun getOtpCodeState() = otpCodeState


    @Composable
    override fun Render(modifier: Modifier, otpElementState: OtpElementState) {
        val otpRow by viewModel.getState().collectAsState()

        OtpRowComposable(
            modifier = modifier.fillMaxWidth(),
            onInput = viewModel::onChange,
            otpRow = otpRow,
            otpElementState = otpElementState
        )
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