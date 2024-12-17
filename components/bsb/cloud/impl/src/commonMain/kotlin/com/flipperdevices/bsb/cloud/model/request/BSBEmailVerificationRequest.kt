package com.flipperdevices.bsb.cloud.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BSBEmailVerificationRequest(
    @SerialName("email")
    val email: String,
    @SerialName("confirm_type")
    val confirmType: String
)
