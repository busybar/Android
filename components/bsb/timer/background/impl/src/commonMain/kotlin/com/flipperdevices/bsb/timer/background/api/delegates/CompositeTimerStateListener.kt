package com.flipperdevices.bsb.timer.background.api.delegates

import com.flipperdevices.bsb.timer.background.api.TimerStateListener
import kotlinx.collections.immutable.minus
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.plus
import kotlinx.collections.immutable.toPersistentList
import kotlinx.collections.immutable.toPersistentSet
import me.tatarka.inject.annotations.Inject

@Inject
class CompositeTimerStateListener(
    buildInListeners: Set<TimerStateListener>
) : TimerStateListener {
    private var listeners = buildInListeners.toPersistentList()

    fun addListener(listener: TimerStateListener) {
        listeners += listener
    }

    fun removeListener(listener: TimerStateListener) {
        listeners -= listener
    }

    override fun onTimerStart() {
        listeners.forEach { listener ->
            runCatching {
                listener.onTimerStart()
            }
        }
    }

    override fun onTimerStop() {
        listeners.forEach { listener ->
            runCatching {
                listener.onTimerStop()
            }
        }
    }
}