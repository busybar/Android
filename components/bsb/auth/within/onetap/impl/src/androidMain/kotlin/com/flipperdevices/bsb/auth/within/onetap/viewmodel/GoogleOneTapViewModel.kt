package com.flipperdevices.bsb.auth.within.onetap.viewmodel

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.flipperdevices.bsb.auth.within.main.model.AuthWay
import com.flipperdevices.bsb.auth.within.main.model.SignWithInState
import com.flipperdevices.bsb.auth.within.main.model.SignWithInStateListener
import com.flipperdevices.bsb.cloud.api.BSBAuthApi
import com.flipperdevices.core.ktx.jre.transform
import com.flipperdevices.core.log.LogTagProvider
import com.flipperdevices.core.log.error
import com.flipperdevices.core.ui.lifecycle.DecomposeViewModel
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

private const val WEB_CLIENT_ID =
    "469670716450-8msk6u44e6of0ph9iq6aog5l8lc586go.apps.googleusercontent.com"

@Inject
class GoogleOneTapViewModel(
    @Assisted private val withInStateListener: SignWithInStateListener,
    private val context: Context,
    private val authApi: BSBAuthApi
) : DecomposeViewModel(), LogTagProvider {
    override val TAG = "GoogleOneTapViewModel"

    private val credentialManager = CredentialManager.create(context)

    fun onAuth() = viewModelScope.launch {
        withInStateListener(SignWithInState.InProgress(AuthWay.GOOGLE))

        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId(WEB_CLIENT_ID)
            .build()
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        runCatching {
            credentialManager.getCredential(
                request = request,
                context = context,
            )
        }.transform { result ->
            handleSignIn(result)
        }.onSuccess {
            withInStateListener(SignWithInState.Complete)
        }.onFailure {
            error(it) { "Failed sign in with google" }
            withInStateListener(SignWithInState.WaitingForInput)
        }
    }

    private suspend fun handleSignIn(
        result: GetCredentialResponse
    ): Result<Unit> {
        when (val credential = result.credential) {
            // GoogleIdToken credential
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        val googleIdTokenCredential = GoogleIdTokenCredential
                            .createFrom(credential.data)

                        return authApi.jwtAuth(googleIdTokenCredential.idToken)
                    } catch (e: GoogleIdTokenParsingException) {
                        return Result.failure(
                            RuntimeException(
                                "Received an invalid google id token response",
                                e
                            )
                        )
                    }
                } else {
                    return Result.failure(RuntimeException("Unexpected type of credential: ${credential.type}"))
                }
            }

            else -> {
                return Result.failure(RuntimeException("Unexpected type of credential: $credential"))
            }
        }
    }
}