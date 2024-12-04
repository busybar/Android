package com.flipperdevices.bsb.auth.within.main.api

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.flipperdevices.bsb.auth.within.main.composable.OrLineComposable
import com.flipperdevices.bsb.auth.within.main.model.SignWithInState
import com.flipperdevices.bsb.auth.within.main.model.SignWithInStateListener
import com.flipperdevices.bsb.auth.within.oauth.api.OAuthElementDecomposeComponent
import com.flipperdevices.bsb.auth.within.oauth.model.OAuthProvider
import com.flipperdevices.bsb.auth.within.onetap.api.GoogleOneTapAuthDecomposeComponent
import com.flipperdevices.core.di.AppGraph
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@Inject
class SignWithInMainDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
    @Assisted withInStateListener: SignWithInStateListener,
    oneTapAuthDecomposeComponent: GoogleOneTapAuthDecomposeComponent.Factory,
    oAuthElementDecomposeComponent: OAuthElementDecomposeComponent.Factory
) : SignWithInMainDecomposeComponent(componentContext),
    ComponentContext by componentContext {
    private val googleAuth = oneTapAuthDecomposeComponent(
        componentContext = childContext("signWithIn_google"),
        withInStateListener = withInStateListener
    )
    private val appleAuth = oAuthElementDecomposeComponent(
        componentContext = childContext("signWithIn_apple"),
        oAuthProvider = OAuthProvider.APPLE,
        withInStateListener = withInStateListener
    )
    private val microsoftAuth = oAuthElementDecomposeComponent(
        componentContext = childContext("signWithIn_microsoft"),
        oAuthProvider = OAuthProvider.MICROSOFT,
        withInStateListener = withInStateListener
    )

    @Composable
    override fun Render(modifier: Modifier, authState: SignWithInState) {
        Column(modifier) {
            OrLineComposable(
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
            )

            Row(
                Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                googleAuth.Render(
                    modifier = Modifier.weight(1f),
                    authState = authState
                )
                appleAuth.Render(
                    modifier = Modifier.weight(1f),
                    authState = authState
                )
                microsoftAuth.Render(
                    modifier = Modifier.weight(1f),
                    authState = authState
                )
            }
        }
    }

    @Inject
    @ContributesBinding(AppGraph::class, SignWithInMainDecomposeComponent.Factory::class)
    class Factory(
        private val factory: (
            componentContext: ComponentContext,
            withInStateListener: SignWithInStateListener
        ) -> SignWithInMainDecomposeComponentImpl
    ) : SignWithInMainDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            withInStateListener: SignWithInStateListener
        ) = factory(componentContext, withInStateListener)
    }
}