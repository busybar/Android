package com.flipperdevices.bsb.timer.background.api.delegates

import com.flipperdevices.bsb.timer.background.api.TimerStateListener
import com.flipperdevices.core.di.AppGraph
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

/**
 * Workaround for DI building issue when timer state set is empty
 *
 * https://github.com/evant/kotlin-inject/issues/249
 */
@Inject
@ContributesBinding(AppGraph::class, TimerStateListener::class, multibinding = true)
class EmptyTimerStateListener : TimerStateListener {
}