package com.flipperdevices.bsb.cloud.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BSBApiEmailVerificationResponse(
    @SerialName("code_lifetime")
    val codeLifetime: Int,
)
