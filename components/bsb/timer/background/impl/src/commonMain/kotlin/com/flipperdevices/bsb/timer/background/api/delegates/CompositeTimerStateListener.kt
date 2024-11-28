package com.flipperdevices.bsb.timer.background.api.delegates

import com.flipperdevices.bsb.timer.background.api.TimerStateListener
import kotlinx.collections.immutable.minus
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.plus
import me.tatarka.inject.annotations.Inject

@Inject
class CompositeTimerStateListener() : TimerStateListener {
    private var listeners = persistentListOf<TimerStateListener>()

    fun addListener(listener: TimerStateListener) {
        listeners += listener
    }

    fun removeListener(listener: TimerStateListener) {
        listeners -= listener
    }

    override fun onTimerStop() {
        listeners.forEach { listener ->
            runCatching {
                listener.onTimerStop()
            }
        }
    }
}