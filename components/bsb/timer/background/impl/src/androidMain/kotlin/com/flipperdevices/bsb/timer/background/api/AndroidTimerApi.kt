package com.flipperdevices.bsb.timer.background.api

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder
import com.flipperdevices.bsb.timer.background.model.ControlledTimerState
import com.flipperdevices.bsb.timer.background.model.TimerAction
import com.flipperdevices.bsb.timer.background.model.TimerState
import com.flipperdevices.bsb.timer.background.service.TimerServiceBinder
import com.flipperdevices.core.di.AppGraph
import com.flipperdevices.core.ktx.jre.withLock
import com.flipperdevices.core.log.LogTagProvider
import com.flipperdevices.core.log.error
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@Inject
@SingleIn(AppGraph::class)
@ContributesBinding(AppGraph::class, TimerApi::class)
class AndroidTimerApi(
    private val scope: CoroutineScope
) : TimerApi, ServiceConnection, LogTagProvider {
    override val TAG = "AndroidTimerApi"

    private val state = MutableStateFlow<ControlledTimerState?>(null)
    private val mutex = Mutex()
    private var binderListenerJob: Job? = null

    override fun startTimer(initialTimerState: TimerState) {
        TODO("Not yet implemented")
    }

    override fun getState() = state.asStateFlow()

    override fun onAction(action: TimerAction) {
        TODO("Not yet implemented")
    }

    override fun stopTimer() {
        TODO("Not yet implemented")
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        val binder = service as? TimerServiceBinder
        if (binder == null) {
            error { "Try to connect with $name failed because binder is not TimerServiceBinder" }
            return
        }
        scope.launch {
            withLock(mutex) {
                binderListenerJob = getJob(binder)
            }
        }
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        TODO("Not yet implemented")
    }

    private fun getJob(binder: TimerServiceBinder) = binder
        .timerApi
        .getState()
        .onEach {
            state.emit(it)
        }.launchIn(scope)
}