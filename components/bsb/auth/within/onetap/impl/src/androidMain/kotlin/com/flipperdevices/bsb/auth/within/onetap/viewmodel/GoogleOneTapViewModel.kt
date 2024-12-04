package com.flipperdevices.bsb.auth.within.onetap.viewmodel

import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetPasswordOption
import com.flipperdevices.bsb.auth.within.main.model.SignWithInState
import com.flipperdevices.bsb.auth.within.main.model.SignWithInStateListener
import com.flipperdevices.core.ui.lifecycle.DecomposeViewModel
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

private const val WEB_CLIENT_ID =
    "469670716450-8msk6u44e6of0ph9iq6aog5l8lc586go.apps.googleusercontent.com"

@Inject
class GoogleOneTapViewModel(
    @Assisted private val withInStateListener: SignWithInStateListener
) : DecomposeViewModel() {
    fun onAuth() = viewModelScope.launch {
        withInStateListener(SignWithInState.IN_PROGRESS)


        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId(WEB_CLIENT_ID)
            .build()
        val passwordOption = GetPasswordOption()
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .addCredentialOption(passwordOption)
            .build()
    }
}