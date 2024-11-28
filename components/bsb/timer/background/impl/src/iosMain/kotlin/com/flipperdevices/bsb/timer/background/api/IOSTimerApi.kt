package com.flipperdevices.bsb.timer.background.api

import com.flipperdevices.core.di.AppGraph
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@Inject
@SingleIn(AppGraph::class)
@ContributesBinding(AppGraph::class, TimerApi::class)
class IOSTimerApi(
    commonTimerApi: CommonTimerApi
) : TimerApi by commonTimerApi {
}