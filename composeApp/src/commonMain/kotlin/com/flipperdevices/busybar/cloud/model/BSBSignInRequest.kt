package com.flipperdevices.busybar.cloud.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BSBSignInRequest(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String
)