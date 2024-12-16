package com.flipperdevices.bsb.cloud.model

data class BSBOAuthInformation(
    val providerUrl: String,
    val handleUrl: String,
    val tokenQueryKey: String
)