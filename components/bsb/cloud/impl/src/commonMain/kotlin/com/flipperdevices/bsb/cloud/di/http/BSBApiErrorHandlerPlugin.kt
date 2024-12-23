package com.flipperdevices.bsb.cloud.di.http

import busystatusbar.components.bsb.cloud.impl.generated.resources.Res
import busystatusbar.components.bsb.cloud.impl.generated.resources.bsb_api_error_undefined
import com.flipperdevices.bsb.cloud.model.exception.BSBApiError
import com.flipperdevices.bsb.cloud.model.exception.BSBApiErrorException
import com.flipperdevices.bsb.cloud.model.response.BSBErrorResponse
import io.ktor.client.call.body
import io.ktor.client.plugins.api.Send
import io.ktor.client.plugins.api.createClientPlugin
import org.jetbrains.compose.resources.StringResource

private const val HTTP_STATUS_CODE_FAILED_FROM = 400

val BSBApiErrorHandlerPlugin = createClientPlugin("BSBApiErrorHandlerPlugin") {
    on(Send) { request ->
        val response = proceed(request)
        if (response.response.status.value > HTTP_STATUS_CODE_FAILED_FROM) {
            val error = response.body<BSBErrorResponse>()
            val enum = errorCodeToEnum(error.error.code)
            throw BSBApiErrorException(
                errorCode = enum,
                localizedError = enum.toStringResource(),
                fallbackErrorDescription = error.error.message
            )
        }

        return@on response
    }
}

private fun errorCodeToEnum(code: Int): BSBApiError {
    return BSBApiError.entries.find {
        it.code == code
    } ?: BSBApiError.UNDEFINED
}

private fun BSBApiError.toStringResource(): StringResource {
    return when (this) {
        BSBApiError.UNDEFINED,
        BSBApiError.UNKNOWN_USER,
        BSBApiError.UNKNOWN_REDIRECT,
        BSBApiError.UNKNOWN_CONFIRMATION_TYPE,
        BSBApiError.AUTH_SESSION_NOT_PROVIDED,
        BSBApiError.AUTH_EXPIRED_SESSION,
        BSBApiError.AUTH_INVALID_TOKEN_CERTIFICATION,
        BSBApiError.AUTH_INVALID_CONFIRMATION_CODE,
        BSBApiError.AUTH_INVALID_USER_PASSWORD,
        BSBApiError.AUTH_INVALID_PROVIDER,
        BSBApiError.AUTH_INVALID_USERINFO,
        BSBApiError.AUTH_INVALID_SESSION,
        BSBApiError.AUTH_INVALID_STATE,
        BSBApiError.AUTH_INVALID_TOKEN,
        BSBApiError.AUTH_USER_EXISTS,
        BSBApiError.UNPROCESSABLE_ENTITY,
        BSBApiError.UNEXPECTED_ERROR -> Res.string.bsb_api_error_undefined
    }
}
