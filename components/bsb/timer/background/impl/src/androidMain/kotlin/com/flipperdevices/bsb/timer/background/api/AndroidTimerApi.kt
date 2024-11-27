package com.flipperdevices.bsb.timer.background.api

import com.flipperdevices.bsb.timer.background.model.ControlledTimerState
import com.flipperdevices.bsb.timer.background.model.TimerAction
import com.flipperdevices.bsb.timer.background.model.TimerState
import com.flipperdevices.core.di.AppGraph
import kotlinx.coroutines.flow.StateFlow
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@Inject
@SingleIn(AppGraph::class)
@ContributesBinding(AppGraph::class, TimerApi::class)
class AndroidTimerApi(
    commonTimerApi: CommonTimerApi
) : TimerApi by commonTimerApi {
}