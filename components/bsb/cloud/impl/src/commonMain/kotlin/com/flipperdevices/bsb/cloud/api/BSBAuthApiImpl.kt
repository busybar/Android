package com.flipperdevices.bsb.cloud.api

import com.flipperdevices.bsb.cloud.model.BSBApiCheckUserRequest
import com.flipperdevices.bsb.cloud.model.BSBApiSignInRequest
import com.flipperdevices.bsb.cloud.model.BSBApiUserObject
import com.flipperdevices.bsb.cloud.model.BSBUser
import com.flipperdevices.bsb.cloud.utils.NetworkConstants
import com.flipperdevices.core.di.AppGraph
import com.flipperdevices.core.ktx.jre.FlipperDispatchers
import com.flipperdevices.core.log.LogTagProvider
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.client.request.setBody
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.withContext
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

private val networkDispatcher = FlipperDispatchers.default

@Inject
@ContributesBinding(AppGraph::class, BSBAuthApi::class)
class BSBAuthApiImpl(
    private val httpClient: HttpClient
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
            }
        }.map { }
    }

    override suspend fun getUser(): Result<BSBUser> = withContext(networkDispatcher) {
        return@withContext runCatching {
            httpClient.get {
                url("${NetworkConstants.BASE_URL}/v0/auth/me")
            }.body<BSBApiUserObject>()
        }.map { BSBUser(it.email) }
    }
}