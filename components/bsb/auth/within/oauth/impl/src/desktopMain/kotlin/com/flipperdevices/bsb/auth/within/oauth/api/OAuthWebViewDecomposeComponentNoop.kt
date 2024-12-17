package com.flipperdevices.bsb.auth.within.oauth.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.bsb.auth.within.oauth.model.OAuthProvider
import com.flipperdevices.core.di.AppGraph
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@Inject
class OAuthWebViewDecomposeComponentNoop(
    @Assisted componentContext: ComponentContext,
) : OAuthWebViewDecomposeComponent(componentContext) {
    @Composable
    override fun Render(modifier: Modifier) = Unit

    @Inject
    @ContributesBinding(AppGraph::class, OAuthWebViewDecomposeComponent.Factory::class)
    class Factory(
        private val factory: (
            componentContext: ComponentContext,
        ) -> OAuthWebViewDecomposeComponentNoop
    ) : OAuthWebViewDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            oAuthProvider: OAuthProvider,
            onComplete: (String) -> Unit
        ) = factory(componentContext)
    }
}
