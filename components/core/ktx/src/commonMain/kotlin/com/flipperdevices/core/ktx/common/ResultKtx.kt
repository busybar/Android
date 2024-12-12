package com.flipperdevices.core.ktx.common

suspend fun <T, R> Result<T>.transform(block: suspend (T) -> Result<R>): Result<R> {
    return mapCatching { firstResult ->
        block(firstResult)
    }.mapCatching { it.getOrThrow() }
}