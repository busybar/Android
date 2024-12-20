package com.flipperdevices.bsb.auth.within.oauth.api

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.bsb.auth.within.oauth.model.OAuthProvider
import com.flipperdevices.bsb.auth.within.oauth.model.toInternal
import com.flipperdevices.bsb.auth.within.oauth.utils.OAuthTokenExtractor
import com.flipperdevices.bsb.cloud.api.BSBAuthApi
import com.flipperdevices.bsb.core.theme.LocalPallet
import com.flipperdevices.core.di.AppGraph
import com.kevinnzou.web.LoadingState
import com.kevinnzou.web.WebView
import com.kevinnzou.web.rememberWebViewNavigator
import com.kevinnzou.web.rememberWebViewState
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@Inject
class OAuthWebViewDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
    @Assisted oAuthProvider: OAuthProvider,
    @Assisted private val onReceiveToken: (String) -> Unit,
    bsbAuthApi: BSBAuthApi
) : OAuthWebViewDecomposeComponent(componentContext) {
    private val internalOAuthProvider = oAuthProvider.toInternal()
    private val oAuthInformation = bsbAuthApi.getUrlForOauth(internalOAuthProvider.apiProvider)

    @SuppressLint("SetJavaScriptEnabled")
    @Composable
    override fun Render(modifier: Modifier) {
        val state = rememberWebViewState(oAuthInformation.providerUrl)
        val navigator = rememberWebViewNavigator()
        val url = state.lastLoadedUrl

        if (url?.startsWith(oAuthInformation.handleUrl) == true) {
            LaunchedEffect(url) {
                val token = OAuthTokenExtractor.extract(url, oAuthInformation)
                if (token != null) {
                    onReceiveToken(token)
                }
            }
            return
        }
        Column(
            modifier = modifier
                .safeContentPadding()
                .fillMaxSize()
        ) {
            val loadingState = state.loadingState
            if (loadingState is LoadingState.Loading) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth(),
                    progress = loadingState.progress,
                    color = LocalPallet.current.black.invert,
                    backgroundColor = LocalPallet.current.transparent.blackInvert.secondary
                )
            }
            WebView(
                modifier = Modifier.fillMaxSize(),
                state = state,
                navigator = navigator,
                onCreated = { it.settings.javaScriptEnabled = true }
            )
        }
    }

    @Inject
    @ContributesBinding(AppGraph::class, OAuthWebViewDecomposeComponent.Factory::class)
    class Factory(
        private val factory: (
            componentContext: ComponentContext,
            oAuthProvider: OAuthProvider,
            onComplete: (String) -> Unit
        ) -> OAuthWebViewDecomposeComponentImpl
    ) : OAuthWebViewDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            oAuthProvider: OAuthProvider,
            onComplete: (String) -> Unit
        ) = factory(componentContext, oAuthProvider, onComplete)
    }
}
