package com.flipperdevices.bsb.cloud.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BSBApiSignInRequest(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String
)