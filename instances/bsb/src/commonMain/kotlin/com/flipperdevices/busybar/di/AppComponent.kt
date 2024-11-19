package com.flipperdevices.busybar.di

import com.flipperdevices.bsb.root.api.RootDecomposeComponent
import com.flipperdevices.core.di.AppGraph
import com.russhwolf.settings.ObservableSettings
import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.MergeComponent
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@MergeComponent(AppGraph::class)
@SingleIn(AppGraph::class)
abstract class AppComponent(
    @get:Provides protected val observableSettings: ObservableSettings
) {
    abstract val rootDecomposeComponentFactory: RootDecomposeComponent.Factory
}

@MergeComponent.CreateComponent
expect fun createAppComponent(
    settings: ObservableSettings
): AppComponent