package com.flipperdevices.core.ktx.common

import kotlinx.coroutines.CoroutineDispatcher

/**
 * To be able to mock dispatchers
 */
object FlipperDispatchers {
    val default: CoroutineDispatcher by lazy { getDispatcher() }
}

internal expect fun getDispatcher(): CoroutineDispatcher
