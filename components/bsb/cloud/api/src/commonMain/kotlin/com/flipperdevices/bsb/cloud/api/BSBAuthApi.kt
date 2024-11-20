package com.flipperdevices.bsb.cloud.api

import com.flipperdevices.bsb.cloud.model.BSBUser

interface BSBAuthApi {
    suspend fun isUserExist(email: String): Result<Boolean>

    suspend fun signIn(
        email: String, password: String
    ): Result<Unit>

    suspend fun getUser(): Result<BSBUser>
}