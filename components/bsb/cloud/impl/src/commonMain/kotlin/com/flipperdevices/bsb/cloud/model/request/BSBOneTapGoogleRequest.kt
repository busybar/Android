package com.flipperdevices.bsb.cloud.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BSBOneTapGoogleRequest(
    @SerialName("jwt_token")
    val token: String
)
