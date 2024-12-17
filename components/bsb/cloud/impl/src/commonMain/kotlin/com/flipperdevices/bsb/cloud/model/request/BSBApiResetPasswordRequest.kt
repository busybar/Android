package com.flipperdevices.bsb.cloud.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BSBApiResetPasswordRequest(
    @SerialName("email")
    val email: String,
    @SerialName("new_password")
    val password: String,
    @SerialName("code")
    val code: String
)
