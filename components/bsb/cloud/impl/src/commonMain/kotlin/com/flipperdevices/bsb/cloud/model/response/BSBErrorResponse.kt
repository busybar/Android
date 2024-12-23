package com.flipperdevices.bsb.cloud.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BSBErrorResponse(
    @SerialName("error")
    val error: BSBApiErrorObject,
)
