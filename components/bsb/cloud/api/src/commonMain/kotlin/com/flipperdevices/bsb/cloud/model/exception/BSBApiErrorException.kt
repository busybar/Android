package com.flipperdevices.bsb.cloud.model.exception

import org.jetbrains.compose.resources.StringResource

class BSBApiErrorException(
    val errorCode: BSBApiError,
    val localizedError: StringResource,
    val fallbackErrorDescription: String?
) : Throwable(fallbackErrorDescription)
