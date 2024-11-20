package com.flipperdevices.core.ktx.jre

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors

internal actual fun getDispatcher(): CoroutineDispatcher {
    return Executors.newWorkStealingPool().asCoroutineDispatcher()
}

