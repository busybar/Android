package com.flipperdevices.bsb.cloud.model

import kotlinx.datetime.Instant

data class BSBEmailVerificationResponse(
    val codeExpiryTime: Instant
)