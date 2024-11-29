package com.flipperdevices.bsb.dnd.api

import com.flipperdevices.core.di.AppGraph
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@Inject
@ContributesBinding(AppGraph::class, BusyDNDApi::class)
class BusyDNDApiImpl : BusyDNDApi {
    override fun tryToEnable() {
    }
}