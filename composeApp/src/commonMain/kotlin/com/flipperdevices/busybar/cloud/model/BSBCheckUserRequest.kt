package com.flipperdevices.busybar.cloud.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BSBCheckUserRequest(
    @SerialName("email")
    val email: String
)