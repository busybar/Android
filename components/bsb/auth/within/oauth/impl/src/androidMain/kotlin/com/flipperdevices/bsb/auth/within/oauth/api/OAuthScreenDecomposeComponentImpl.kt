package com.flipperdevices.bsb.auth.within.oauth.api

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.bsb.auth.within.common.composable.SignInWithButtonComposable
import com.flipperdevices.bsb.auth.within.main.model.SignWithInState
import com.flipperdevices.bsb.auth.within.main.model.SignWithInStateListener
import com.flipperdevices.bsb.auth.within.oauth.model.OAuthProvider
import com.flipperdevices.bsb.auth.within.oauth.model.toInternal
import com.flipperdevices.core.di.AppGraph
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@Inject
class OAuthScreenDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
    @Assisted oAuthProvider: OAuthProvider,
    @Assisted withInStateListener: SignWithInStateListener
) : OAuthScreenDecomposeComponent(componentContext) {
    private val provider = oAuthProvider.toInternal()

    @Composable
    override fun Render(modifier: Modifier, authState: SignWithInState) {
        SignInWithButtonComposable(
            modifier = modifier,
            icon = if (MaterialTheme.colors.isLight) {
                provider.iconId
            } else {
                provider.darkIconId
            },
            onClick = {}
        )
    }

    @Inject
    @ContributesBinding(AppGraph::class, OAuthScreenDecomposeComponent.Factory::class)
    class Factory(
        private val factory: (
            componentContext: ComponentContext,
            oAuthProvider: OAuthProvider,
            withInStateListener: SignWithInStateListener
        ) -> OAuthScreenDecomposeComponentImpl
    ) : OAuthScreenDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            oAuthProvider: OAuthProvider,
            withInStateListener: SignWithInStateListener
        ) = factory(componentContext, oAuthProvider, withInStateListener)
    }

}