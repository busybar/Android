package com.flipperdevices.bsb.cloud.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BSBApiToken(
    @SerialName("token")
    val token: String
)