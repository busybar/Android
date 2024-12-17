package com.flipperdevices.bsb.cloud.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BSBApiSignInResponse(
    @SerialName("user_data")
    val user: BSBApiUserObject,
    @SerialName("token_data")
    val token: BSBApiToken
)