package com.flipperdevices.bsb.timer.main.viewmodel

import com.flipperdevices.bsb.timer.main.model.TimerAction
import com.flipperdevices.bsb.timer.main.model.TimerControlledState
import com.flipperdevices.bsb.timer.setup.model.TimerState
import com.flipperdevices.core.ktx.jre.withLock
import com.flipperdevices.core.log.LogTagProvider
import com.flipperdevices.core.ui.lifecycle.DecomposeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import kotlin.math.min
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

@Inject
class BusyTimerViewModel(
    @Assisted initialTimerState: TimerState,
    @Assisted onComplete: () -> Unit
) : DecomposeViewModel(), LogTagProvider {
    override val TAG = "BusyTimerViewModel"

    private val startTimerTime = Clock.System.now()

    private val mutex = Mutex()
    private val timer = MutableStateFlow(TimerControlledState(initialTimerState))
    private val initialTimerStateFlow = MutableStateFlow(initialTimerState)

    init {
        viewModelScope.launch {
            combine(
                flow {
                    while (true) {
                        emit(Unit)
                        delay(1.seconds)
                    }
                },
                initialTimerStateFlow
            ) { _, initialTimerState ->
                initialTimerState
            }.collect { initialTimerState ->
                withLock(mutex) {
                    timer.update { original ->
                        if (original.pauseOn != null) {
                            original
                        } else {
                            val delta =
                                Clock.System.now().epochSeconds - startTimerTime.epochSeconds
                            val duration = initialTimerState.toDuration() - delta.seconds
                            original.copy(timerState = duration.toTimerState())
                        }
                    }
                }
            }
        }
        timer.onEach {
            if (it.timerState.minute <= 0 && it.timerState.second <= 0) {
                withContext(Dispatchers.Main) {
                    onComplete()
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getState() = timer.asStateFlow()

    fun onAction(action: TimerAction) = viewModelScope.launch {
        when (action) {
            TimerAction.MINUS -> withLock(mutex) {
                initialTimerStateFlow.update {
                    it.add(TimerState(minute = 0, second = -5))
                }
            }

            TimerAction.PLUS -> withLock(mutex) {
                initialTimerStateFlow.update {
                    it.add(TimerState(minute = 0, second = 5))
                }
            }

            TimerAction.PAUSE -> onPause()
        }
    }

    private suspend fun onPause() = withLock(mutex) {
        timer.update { original ->
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
        seconds += secondsInMinute + seconds
    } else if (seconds >= secondsInMinute) {
        minutes += seconds.div(secondsInMinute)
        seconds += seconds.mod(secondsInMinute)
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