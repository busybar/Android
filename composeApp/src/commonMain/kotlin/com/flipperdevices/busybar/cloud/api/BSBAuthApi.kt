package com.flipperdevices.busybar.cloud.api

import com.flipperdevices.busybar.cloud.model.BSBCheckUserRequest
import io.ktor.client.HttpClient
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
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
) {
    suspend fun isUserExist(email: String): Boolean = withContext(networkDispatcher) {
        val result = runCatching {
            httpClient.post {
                url("${NetworkConstants.BASE_URL}/v0/auth/sign-up/check-user")
                setBody(BSBCheckUserRequest(email))
            }
        }
        if (result.isSuccess) {
            return@withContext true
        } else {
            val exception = result.exceptionOrNull() ?: error("Fail receive error")
            if (exception is ClientRequestException
                && exception.response.status == HttpStatusCode.NotFound
            ) {
                return@withContext false
            }
            throw exception
        }
    }
}