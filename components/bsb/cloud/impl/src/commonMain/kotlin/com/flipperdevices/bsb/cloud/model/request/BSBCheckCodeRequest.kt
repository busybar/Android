package com.flipperdevices.bsb.cloud.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BSBCheckCodeRequest(
    @SerialName("email")
    val email: String,
    @SerialName("code")
    val code: String
)