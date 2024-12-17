package com.flipperdevices.bsb.auth.within.oauth.viewmodel

import com.flipperdevices.bsb.auth.within.main.model.SignWithInState
import com.flipperdevices.bsb.auth.within.main.model.SignWithInStateListener
import com.flipperdevices.bsb.auth.within.oauth.model.InternalOAuthProvider
import com.flipperdevices.bsb.cloud.api.BSBAuthApi
import com.flipperdevices.core.log.LogTagProvider
import com.flipperdevices.core.log.error
import com.flipperdevices.core.log.info
import com.flipperdevices.core.ui.lifecycle.DecomposeViewModel
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class OAuthElementViewModel(
    @Assisted private val withInStateListener: SignWithInStateListener,
    @Assisted private val oAuthProvider: InternalOAuthProvider,
    private val bsbAuthApi: BSBAuthApi
) : DecomposeViewModel(), LogTagProvider {
    override val TAG = "OAuthElementViewModel"

    fun onTokenReceive(token: String) {
        info { "Receive token with length ${token.length}" }
        withInStateListener(SignWithInState.InProgress(oAuthProvider.authWay))
        viewModelScope.launch {
            bsbAuthApi.signIn(token)
                .onSuccess {
                    withInStateListener(SignWithInState.Complete)
                }
                .onFailure {
                    error(it) { "Fail to signin with token" }
                    withInStateListener(SignWithInState.WaitingForInput)
                }
        }
    }
}
