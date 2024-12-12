package com.flipperdevices.core.ktx.common

import com.flipperdevices.core.log.LogTagProvider
import com.flipperdevices.core.log.info
import com.flipperdevices.core.log.verbose
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.datetime.Clock

fun LogTagProvider.launchWithLock(
    mutex: Mutex,
    scope: CoroutineScope,
    tag: String? = null,
    action: suspend CoroutineScope.() -> Unit
) {
    scope.launch(FlipperDispatchers.default) {
        withLock(mutex, tag) { action.invoke(this) }
    }
}

suspend fun LogTagProvider.withLock(
    mutex: Mutex,
    tag: String? = null,
    action: suspend () -> Unit
): Unit = withLockResult(mutex, tag, action)

suspend fun <T> LogTagProvider.withLockResult(
    mutex: Mutex,
    tag: String? = null,
    action: suspend () -> T
): T {
    if (mutex.isLocked) {
        info { "I can't execute right now job $tag because $mutex is locked" }
    }
    return mutex.withLock {
        var startTime: Long = 0
        verbose {
            startTime = Clock.System.now().toEpochMilliseconds()
            "Launch $tag job in mutex mode..."
        }
        val result = action()
        verbose { "Complete $tag job in ${Clock.System.now().toEpochMilliseconds() - startTime}ms" }
        return@withLock result
    }
}
