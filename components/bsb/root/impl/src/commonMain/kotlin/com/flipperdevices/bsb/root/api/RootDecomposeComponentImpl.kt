package com.flipperdevices.bsb.root.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.childStack
import com.flipperdevices.bsb.auth.main.api.AuthDecomposeComponent
import com.flipperdevices.bsb.root.model.RootNavigationConfig
import com.flipperdevices.bsb.timer.main.api.TimerMainDecomposeComponent
import com.flipperdevices.core.di.AppGraph
import com.flipperdevices.ui.decompose.DecomposeComponent
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@Inject
class RootDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
    private val authDecomposeComponentFactory: AuthDecomposeComponent.Factory,
    private val timerMainDecomposeComponentFactory: TimerMainDecomposeComponent.Factory
) : RootDecomposeComponent(),
    ComponentContext by componentContext {
    override val stack = childStack(
        source = navigation,
        serializer = RootNavigationConfig.serializer(),
        initialConfiguration = RootNavigationConfig.Timer,
        handleBackButton = true,
        childFactory = ::child,
    )

    private fun child(
        config: RootNavigationConfig,
        componentContext: ComponentContext
    ): DecomposeComponent = when (config) {
        RootNavigationConfig.Auth -> authDecomposeComponentFactory(
            componentContext
        )

        RootNavigationConfig.Timer -> timerMainDecomposeComponentFactory(
            componentContext
        )
    }


    @Inject
    @ContributesBinding(AppGraph::class, RootDecomposeComponent.Factory::class)
    class Factory(
        private val factory: (
            componentContext: ComponentContext
        ) -> RootDecomposeComponentImpl
    ) : RootDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext
        ) = factory(componentContext)
    }
}