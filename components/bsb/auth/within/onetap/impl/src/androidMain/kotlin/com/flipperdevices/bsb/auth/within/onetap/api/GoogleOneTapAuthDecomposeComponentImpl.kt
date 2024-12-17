package com.flipperdevices.bsb.auth.within.onetap.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import busystatusbar.components.bsb.auth.within.onetap.impl.generated.resources.Res
import busystatusbar.components.bsb.auth.within.onetap.impl.generated.resources.ic_google
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.bsb.auth.within.common.composable.SignInWithButtonComposable
import com.flipperdevices.bsb.auth.within.main.model.AuthWay
import com.flipperdevices.bsb.auth.within.main.model.SignWithInState
import com.flipperdevices.bsb.auth.within.main.model.SignWithInStateListener
import com.flipperdevices.bsb.auth.within.onetap.viewmodel.GoogleOneTapViewModel
import com.flipperdevices.core.di.AppGraph
import com.flipperdevices.core.ui.lifecycle.viewModelWithFactory
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@Inject
class GoogleOneTapAuthDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
    @Assisted withInStateListener: SignWithInStateListener,
    googleOneTapViewModel: (SignWithInStateListener) -> GoogleOneTapViewModel
) : GoogleOneTapAuthDecomposeComponent(componentContext) {
    private val viewModel = viewModelWithFactory(withInStateListener) {
        googleOneTapViewModel(withInStateListener)
    }

    @Composable
    override fun Render(modifier: Modifier, authState: SignWithInState) {
        SignInWithButtonComposable(
            modifier = modifier,
            icon = Res.drawable.ic_google,
            onClick = {
                if (authState == SignWithInState.WaitingForInput) {
                    viewModel.onAuth()
                }
            },
            inProgress = authState is SignWithInState.InProgress
                    && authState.authWay == AuthWay.GOOGLE
        )
    }

    @Inject
    @ContributesBinding(AppGraph::class, GoogleOneTapAuthDecomposeComponent.Factory::class)
    class Factory(
        private val factory: (
            componentContext: ComponentContext,
            withInStateListener: SignWithInStateListener
        ) -> GoogleOneTapAuthDecomposeComponentImpl
    ) : GoogleOneTapAuthDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            withInStateListener: SignWithInStateListener
        ) = factory(componentContext, withInStateListener)
    }

}