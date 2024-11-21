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
                    val duration = startTimerTime - Clock.System.now()
                    duration.toTimerState(initialTimerState)
                }
            }
        }
    }

    fun getState() = timer.asStateFlow()
}

fun Duration.toTimerState(initialTimerState: TimerState): TimerState {
    var second = initialTimerState.second - inWholeSeconds - inWholeMinutes.minutes.inWholeSeconds
    if (second < 0) {
        second += 1.minutes.inWholeSeconds
    }
    return TimerState(
        second = second.toInt(),
        minute = (initialTimerState.minute - inWholeMinutes).toInt()
    )
}