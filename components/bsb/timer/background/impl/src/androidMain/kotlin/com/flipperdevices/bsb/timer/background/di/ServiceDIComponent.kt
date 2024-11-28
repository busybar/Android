package com.flipperdevices.bsb.timer.background.di

import com.flipperdevices.bsb.timer.background.api.CommonTimerApi
import com.flipperdevices.bsb.timer.background.api.TimerApi
import com.flipperdevices.core.di.AppGraph
import software.amazon.lastmile.kotlin.inject.anvil.ContributesTo

@ContributesTo(AppGraph::class)
interface ServiceDIComponent {
    val commonTimerApi: CommonTimerApi
}