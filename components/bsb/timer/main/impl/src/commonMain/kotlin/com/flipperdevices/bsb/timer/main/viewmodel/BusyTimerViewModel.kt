package com.flipperdevices.bsb.timer.main.viewmodel

import com.flipperdevices.bsb.timer.setup.model.TimerState
import com.flipperdevices.core.ui.lifecycle.DecomposeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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
    @Assisted private val initialTimerState: TimerState,
) : DecomposeViewModel() {
    private val startTimerTime = Clock.System.now()
    private val timer = MutableStateFlow(initialTimerState)

    init {
        viewModelScope.launch {
            flow {
                while (true) {
                    emit(Unit)
                    delay(1.seconds)
                }
            }.collect {
                timer.update {
                    val delta = Clock.System.now().epochSeconds - startTimerTime.epochSeconds
                    val duration = initialTimerState.toDuration() - delta.seconds
                    duration.toTimerState()
                }
            }
        }
    }

    fun getState() = timer.asStateFlow()
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