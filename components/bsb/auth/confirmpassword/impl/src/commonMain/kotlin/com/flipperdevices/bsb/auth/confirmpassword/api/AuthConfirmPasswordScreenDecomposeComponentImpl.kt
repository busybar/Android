package com.flipperdevices.bsb.auth.confirmpassword.api

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.bsb.auth.common.composable.appbar.LogInAppBarComposable
import com.flipperdevices.bsb.auth.confirmpassword.composable.ConfirmPasswordScreenComposable
import com.flipperdevices.bsb.auth.confirmpassword.model.ConfirmPasswordType
import com.flipperdevices.bsb.auth.confirmpassword.model.InternalConfirmPasswordType
import com.flipperdevices.bsb.auth.confirmpassword.model.toInternalPasswordType
import com.flipperdevices.bsb.auth.confirmpassword.viewmodel.ConfirmPasswordViewModel
import com.flipperdevices.bsb.auth.confirmpassword.viewmodel.FieldValidationViewModel
import com.flipperdevices.core.di.AppGraph
import com.flipperdevices.core.ui.lifecycle.viewModelWithFactory
import com.flipperdevices.ui.decompose.DecomposeOnBackParameter
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@Inject
class AuthConfirmPasswordScreenDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
    @Assisted type: ConfirmPasswordType,
    @Assisted private val onBackParameter: DecomposeOnBackParameter,
    @Assisted onComplete: () -> Unit,
    fieldViewModelFactory: (preFilledPassword: String?) -> FieldValidationViewModel,
    confirmPasswordViewModelFactory: (
        confirmPasswordType: InternalConfirmPasswordType,
        onComplete: () -> Unit
    ) -> ConfirmPasswordViewModel
) : AuthConfirmPasswordScreenDecomposeComponent(componentContext) {
    private val confirmPasswordType = type.toInternalPasswordType()

    private val fieldViewModel = viewModelWithFactory(type.preFilledPassword) {
        fieldViewModelFactory(type.preFilledPassword)
    }

    private val confirmPasswordViewModel = viewModelWithFactory(
        confirmPasswordType
    ) {
        confirmPasswordViewModelFactory(confirmPasswordType, onComplete)
    }

    @Composable
    override fun Render(modifier: Modifier) {
        Column(
            modifier.fillMaxSize()
                .navigationBarsPadding()
        ) {
            LogInAppBarComposable(
                text = confirmPasswordType.textTitle,
                onBack = onBackParameter::invoke
            )
            val fieldsState by fieldViewModel.getState().collectAsState()
            val screenState by confirmPasswordViewModel.getState().collectAsState()

            ConfirmPasswordScreenComposable(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                fieldsState = fieldsState,
                onPasswordFieldChange = fieldViewModel::onPasswordFieldChange,
                onConfirmFieldChange = fieldViewModel::onConfirmFieldChange,
                onBack = onBackParameter::invoke,
                onConfirm = { confirmPasswordViewModel.onPressConfirm(fieldsState) },
                confirmPasswordType = confirmPasswordType,
                screenState = screenState
            )
        }
    }

    @Inject
    @ContributesBinding(AppGraph::class, AuthConfirmPasswordScreenDecomposeComponent.Factory::class)
    class Factory(
        private val factory: (
            componentContext: ComponentContext,
            type: ConfirmPasswordType,
            onBackParameter: DecomposeOnBackParameter,
            onComplete: () -> Unit,
        ) -> AuthConfirmPasswordScreenDecomposeComponentImpl
    ) : AuthConfirmPasswordScreenDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            type: ConfirmPasswordType,
            onBackParameter: DecomposeOnBackParameter,
            onComplete: () -> Unit
        ) = factory(componentContext, type, onBackParameter, onComplete)
    }
}