package com.flipperdevices.bsb.cloud.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BSBApiErrorObject(
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String?
)
