package com.flipperdevices.core.ui.lifecycle

import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.flipperdevices.core.ktx.common.FlipperDispatchers
import com.flipperdevices.core.ktx.common.launchWithLock
import com.flipperdevices.core.log.LogTagProvider
import com.flipperdevices.core.log.error
import com.flipperdevices.core.log.info
import com.flipperdevices.core.log.warn
import kotlinx.atomicfu.atomic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.withContext

/**
 * This class allows you to run one and only one task
 * at a time with coroutine scope and flipper ble
 */
abstract class OneTimeExecutionTask<INPUT, STATE> : TaskWithLifecycle(), LogTagProvider {
    private val taskScope = coroutineScope(FlipperDispatchers.default)
    private val mutex = Mutex()
    private var job: Job? = null
    private val isAlreadyLaunched = atomic(false)

    fun start(
        input: INPUT,
        stateListener: suspend (STATE) -> Unit
    ) = taskScope.launch(Dispatchers.Main) {
        info { "Called start" }
        if (!isAlreadyLaunched.compareAndSet(false, true)) {
            warn { "OneTimeExecutionBleTask call again" }
            return@launch
        }
        launchWithLock(mutex, taskScope) {
            job?.cancelAndJoin()
            job = null
            job = taskScope.launch(FlipperDispatchers.default) {
                val localScope = this
                // Waiting to be connected to the flipper
                try {
                    startInternal(localScope, input, stateListener)
                } catch (throwable: Throwable) {
                    error(throwable) { "Error during execution" }
                    withContext(Dispatchers.Main) {
                        onStop()
                    }
                }
            }
        }
        onStart()
        taskScope.launch(FlipperDispatchers.default) {
            try {
                awaitCancellation()
            } finally {
                withContext(NonCancellable) {
                    info { "onStopAsync" }
                    onStopAsync(stateListener)
                }
            }
        }
    }

    abstract suspend fun startInternal(
        scope: CoroutineScope,
        input: INPUT,
        stateListener: suspend (STATE) -> Unit
    )

    abstract suspend fun onStopAsync(stateListener: suspend (STATE) -> Unit)
}
