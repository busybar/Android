package com.flipperdevices.inappnotification.impl.storage

import com.flipperdevices.core.ktx.common.withLock
import com.flipperdevices.core.log.LogTagProvider
import com.flipperdevices.core.log.error
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlin.time.Duration

class TimerTask(
    private val delayDuration: Duration,
    private val coroutineScope: CoroutineScope,
    private val block: suspend () -> Unit
) : LogTagProvider {
    override val TAG = "TimerTask"

    private val mutex = Mutex()
    private var job: Job? = null

    suspend fun start() = withLock(mutex, "start") {
        if (job == null) {
            job = coroutineScope.launch {
                launchTimer()
            }
        }
    }

    suspend fun shutdown() = withLock(mutex, "shutdown") {
        job?.cancelAndJoin()
    }

    private suspend fun CoroutineScope.launchTimer() {
        while (isActive) {
            try {
                block()
            } catch (blockExecutionError: Exception) {
                error(blockExecutionError) {
                    "While execute code block in timer we have error"
                }
            }
            delay(delayDuration)
        }
    }
}
