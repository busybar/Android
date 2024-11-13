package com.flipperdevices.busybar.cloud.api

import com.flipperdevices.busybar.cloud.model.BSBCheckUserRequest
import com.flipperdevices.busybar.cloud.model.BSBSignInRequest
import com.flipperdevices.busybar.cloud.model.BSBUserObject
import com.flipperdevices.busybar.core.log.LogTagProvider
import com.flipperdevices.busybar.core.log.info
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.tatarka.inject.annotations.Inject

private val networkDispatcher = Dispatchers.Default

@Inject
class BSBAuthApi(
    private val httpClient: HttpClient
) : LogTagProvider {
    override val TAG = "BSBAuthApi"

    suspend fun isUserExist(email: String): Result<Boolean> = withContext(networkDispatcher) {
        runCatching {
            httpClient.post {
                url("${NetworkConstants.BASE_URL}/v0/auth/sign-up/check-user")
                setBody(BSBCheckUserRequest(email))
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

    suspend fun signIn(
        email: String, password: String
    ): Result<Unit> = withContext(networkDispatcher) {
        runCatching {
            httpClient.post {
                url("${NetworkConstants.BASE_URL}/v0/auth/sign-in")
                setBody(BSBSignInRequest(email, password))
            }
        }.map { }
    }

    suspend fun getUser(): Result<BSBUserObject> = withContext(networkDispatcher) {
        return@withContext runCatching {
            httpClient.get {
                url("${NetworkConstants.BASE_URL}/v0/auth/me")
            }.body<BSBUserObject>()
        }
    }
}