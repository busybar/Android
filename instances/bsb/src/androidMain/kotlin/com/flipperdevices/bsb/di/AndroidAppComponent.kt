package com.flipperdevices.bsb.di

import com.flipperdevices.core.di.AppGraph
import com.russhwolf.settings.ObservableSettings
import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.MergeComponent
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@MergeComponent(AppGraph::class)
@SingleIn(AppGraph::class)
abstract class AndroidAppComponent(
    @get:Provides protected val observableSettings: ObservableSettings
) : AppComponent {
}