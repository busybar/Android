package com.flipperdevices.bsb.timer.background.api

import com.flipperdevices.bsb.timer.background.model.ControlledTimerState
import com.flipperdevices.bsb.timer.background.model.TimerAction
import com.flipperdevices.bsb.timer.background.model.TimerState
import com.flipperdevices.bsb.timer.background.model.toPublicState
import com.flipperdevices.core.di.AppGraph
import com.flipperdevices.core.ktx.jre.withLock
import com.flipperdevices.core.log.LogTagProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesTo
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@Inject
@SingleIn(AppGraph::class)
class CommonTimerApi(
    private val scope: CoroutineScope
) : TimerApi, LogTagProvider {
    override val TAG = "CommonTimerApi"

    private val startTimerTime = MutableStateFlow<Instant?>(null)

    private val mutex = Mutex()
    private val state = MutableStateFlow<ControlledTimerState?>(null)
    private val initialTimerStateFlow = MutableStateFlow<TimerState?>(null)

    private var timerJob: TimerLoopJob? = null
    private var stateInvalidateJob: Job? = null

    init {
        combine(
            startTimerTime,
            initialTimerStateFlow
        ) { startTime, initialTimerState ->
            if (startTime != null && initialTimerState != null) {
                withLock(mutex) {
                    stateInvalidateJob?.cancelAndJoin()
                    timerJob?.cancelAndJoin()
                    val timer = TimerLoopJob(scope, startTime, initialTimerState)
                    timerJob = timer
                    stateInvalidateJob = timer.getInternalState()
                        .onEach { internalState ->
                            if (internalState.timerState.minute <= 0 && internalState.timerState.second <= 0) {
                                state.emit(null)
                            } else {
                                state.emit(internalState.toPublicState())
                            }
                        }.launchIn(scope)
                }
            } else {
                withLock(mutex) {
                    stateInvalidateJob?.cancelAndJoin()
                    timerJob?.cancelAndJoin()
                    timerJob = null
                    stateInvalidateJob = null
                    state.emit(null)
                }
            }
        }.launchIn(scope)
    }

    override fun startTimer(initialTimerState: TimerState) {
        scope.launch {
            withLock(mutex) {
                startTimerTime.emit(Clock.System.now())
                initialTimerStateFlow.emit(initialTimerState)
            }
        }
    }

    override fun getState() = state.asStateFlow()

    override fun onAction(action: TimerAction) {
        scope.launch {
            withLock(mutex) {
                timerJob?.onAction(action)
            }
        }
    }

    override fun stopTimer() {
        scope.launch {
            withLock(mutex) {
                startTimerTime.emit(null)
                initialTimerStateFlow.emit(null)
            }
        }
    }
}