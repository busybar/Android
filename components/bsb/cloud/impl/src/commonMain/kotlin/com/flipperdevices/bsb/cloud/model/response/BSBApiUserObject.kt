package com.flipperdevices.bsb.cloud.model.response

import com.flipperdevices.bsb.cloud.utils.DateSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BSBApiUserObject(
    @SerialName("uid")
    val uid: String,
    @SerialName("username")
    val username: String?,
    @SerialName("display_name")
    val displayName: String?,
    @SerialName("email")
    val email: String,
    @SerialName("created_at")
    @Serializable(with = DateSerializer::class)
    val createdAt: LocalDateTime,
    @SerialName("updated_at")
    @Serializable(with = DateSerializer::class)
    val updatedAt: LocalDateTime,

)
