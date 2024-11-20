package com.flipperdevices.core.ktx.jre

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal actual fun getDispatcher(): CoroutineDispatcher {
    return Dispatchers.Default
}

