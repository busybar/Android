package com.flipperdevices.bsb.cloud.api

import com.flipperdevices.bsb.cloud.model.request.BSBApiCheckUserRequest
import com.flipperdevices.bsb.cloud.model.request.BSBApiSignInRequest
import com.flipperdevices.bsb.cloud.model.BSBApiToken
import com.flipperdevices.bsb.cloud.model.BSBApiUserObject
import com.flipperdevices.bsb.cloud.model.BSBEmailVerificationResponse
import com.flipperdevices.bsb.cloud.model.BSBEmailVerificationType
import com.flipperdevices.bsb.cloud.model.BSBResponse
import com.flipperdevices.bsb.cloud.model.request.BSBOneTapGoogleRequest
import com.flipperdevices.bsb.cloud.model.BSBUser
import com.flipperdevices.bsb.cloud.model.request.BSBCheckCodeRequest
import com.flipperdevices.bsb.cloud.model.request.BSBEmailVerificationRequest
import com.flipperdevices.bsb.cloud.model.response.BSBApiEmailVerificationResponse
import com.flipperdevices.bsb.cloud.model.toVerificationTypeString
import com.flipperdevices.bsb.cloud.utils.NetworkConstants
import com.flipperdevices.bsb.preference.api.PreferenceApi
import com.flipperdevices.bsb.preference.api.set
import com.flipperdevices.bsb.preference.model.SettingsEnum
import com.flipperdevices.core.di.AppGraph
import com.flipperdevices.core.ktx.common.FlipperDispatchers
import com.flipperdevices.core.ktx.common.transform
import com.flipperdevices.core.log.LogTagProvider
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.client.request.setBody
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

private val networkDispatcher = FlipperDispatchers.default

@Inject
@ContributesBinding(AppGraph::class, BSBAuthApi::class)
class BSBAuthApiImpl(
    private val httpClient: HttpClient,
    private val preferenceApi: PreferenceApi
) : BSBAuthApi, LogTagProvider {
    override val TAG = "BSBAuthApi"

    override suspend fun isUserExist(email: String): Result<Boolean> =
        withContext(networkDispatcher) {
            runCatching {
                httpClient.post {
                    url("${NetworkConstants.BASE_URL}/v0/auth/sign-up/check-user")
                    setBody(BSBApiCheckUserRequest(email))
                }
            }.map { true }
                .recoverCatching { exception ->
                    if (exception is ClientRequestException
                        && exception.response.status == HttpStatusCode.NotFound
                    ) {
                        return@withContext Result.success(false)
                    }
                    return@withContext Result.failure(exception)
                }
        }

    override suspend fun signIn(
        email: String, password: String
    ): Result<Unit> = withContext(networkDispatcher) {
        runCatching {
            httpClient.post {
                url("${NetworkConstants.BASE_URL}/v0/auth/sign-in")
                setBody(BSBApiSignInRequest(email, password))
            }.body<BSBApiToken>()
        }.onSuccess {
            preferenceApi.setString(SettingsEnum.AUTH_TOKEN, it.token)
        }.transform { getUser() }
            .onSuccess { bsbUser ->
                preferenceApi.set(SettingsEnum.USER_DATA, bsbUser)
            }.map { }
    }

    override suspend fun getUser(): Result<BSBUser> = withContext(networkDispatcher) {
        return@withContext runCatching {
            httpClient.get {
                url("${NetworkConstants.BASE_URL}/v0/auth/me")
            }.body<BSBApiUserObject>()
        }.map { BSBUser(it.email) }
    }

    override suspend fun jwtAuth(token: String): Result<Unit> = withContext(networkDispatcher) {
        return@withContext runCatching {
            httpClient.post {
                url("${NetworkConstants.BASE_URL}/v0/oauth2/google/one-tap")
                setBody(BSBOneTapGoogleRequest(token))
            }.body<BSBApiToken>()
        }.onSuccess {
            preferenceApi.setString(SettingsEnum.AUTH_TOKEN, it.token)
        }.transform { getUser() }
            .onSuccess { bsbUser ->
                preferenceApi.set(SettingsEnum.USER_DATA, bsbUser)
            }.map { }
    }

    override suspend fun requestVerifyEmail(
        email: String,
        verificationType: BSBEmailVerificationType
    ): Result<BSBEmailVerificationResponse> = withContext(networkDispatcher) {
        return@withContext runCatching {
            httpClient.post {
                url("${NetworkConstants.BASE_URL}/v0/auth/verify-email")
                setBody(
                    BSBEmailVerificationRequest(
                        email,
                        verificationType.toVerificationTypeString()
                    )
                )
            }.body<BSBResponse<BSBApiEmailVerificationResponse>>()
        }.map {
            BSBEmailVerificationResponse(
                Instant.fromEpochSeconds(
                    Clock.System.now().epochSeconds + it.response.codeLifetime
                )
            )
        }
    }

    override suspend fun checkCode(
        email: String,
        code: String,
        verificationType: BSBEmailVerificationType
    ): Result<Unit> = withContext(networkDispatcher) {
        return@withContext runCatching {
            httpClient.post {
                url("${NetworkConstants.BASE_URL}/v0/auth/check-code")
                parameter("confirm_type", verificationType.toVerificationTypeString())
                setBody(BSBCheckCodeRequest(email, code))
            }.body<BSBResponse<*>>()
        }.map { }
    }
}