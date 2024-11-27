package com.flipperdevices.bsb.timer.background.api

import com.flipperdevices.bsb.timer.background.model.InternalControlledTimerState
import com.flipperdevices.bsb.timer.background.model.TimerAction
import com.flipperdevices.bsb.timer.background.model.TimerState
import com.flipperdevices.core.ktx.jre.withLock
import com.flipperdevices.core.log.LogTagProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.sync.Mutex
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class TimerLoopJob(
    scope: CoroutineScope,
    private val startTime: Instant,
    initialTimerState: TimerState
) : LogTagProvider {
    override val TAG = "TimerLoopJob"

    private val timerStateFlow = MutableStateFlow(
        InternalControlledTimerState(
            timerState = initialTimerState,
            pauseOn = null
        )
    )
    private val initialTimerStateFlow = MutableStateFlow(initialTimerState)
    private val mutex = Mutex()

    internal fun getInternalState() = timerStateFlow.asStateFlow()

    private val job = combine(
        flow {
            while (true) {
                emit(Unit)
                delay(1.seconds)
            }
        },
        initialTimerStateFlow
    ) { _, initialTimerState ->
        withLock(mutex) {
            timerStateFlow.update { original ->
                if (original.pauseOn != null) {
                    original
                } else {
                    val delta =
                        Clock.System.now().epochSeconds - startTime.epochSeconds
                    val duration = initialTimerState.toDuration() - delta.seconds
                    original.copy(timerState = duration.toTimerState())
                }
            }
        }
    }.launchIn(scope)

    suspend fun cancelAndJoin() {
        job.cancelAndJoin()
    }

    suspend fun onAction(action: TimerAction) = withLock(mutex) {
        when (action) {
            TimerAction.MINUS -> addTime(TimerState(minute = 0, second = -5))
            TimerAction.PLUS -> addTime(TimerState(minute = 0, second = 5))
            TimerAction.PAUSE -> onPause()
        }
    }

    private fun addTime(stateToAdd: TimerState) {
        initialTimerStateFlow.update {
            it.add(stateToAdd)
        }
        timerStateFlow.update { currentTimer ->
            if (currentTimer.pauseOn != null) {
                currentTimer.copy(timerState = currentTimer.timerState.add(stateToAdd))
            } else currentTimer
        }
    }

    private fun onPause() {
        timerStateFlow.update { original ->
            if (original.pauseOn == null) {
                original.copy(pauseOn = Clock.System.now())
            } else {
                initialTimerStateFlow.update { originalInitialState ->
                    val delta = Clock.System.now() - original.pauseOn
                    originalInitialState.add(delta.toTimerState())
                }
                original.copy(pauseOn = null)
            }
        }
    }
}

fun TimerState.add(timerState: TimerState): TimerState {
    var minutes = minute + timerState.minute
    var seconds = second + timerState.second
    val secondsInMinute = 1.minutes.inWholeSeconds.toInt()
    if (seconds < 0) {
        minutes -= 1
        seconds += secondsInMinute
    } else if (seconds >= secondsInMinute) {
        minutes += seconds.div(secondsInMinute)
        seconds = seconds.mod(secondsInMinute)
    }

    return TimerState(
        minute = minutes,
        second = seconds
    )
}

fun TimerState.toDuration(): Duration {
    return minute.minutes + second.seconds
}

fun Duration.toTimerState(): TimerState {
    val seconds = inWholeSeconds - inWholeMinutes * 1.minutes.inWholeSeconds

    return TimerState(
        second = seconds.toInt(),
        minute = inWholeMinutes.toInt()
    )
}