package com.flipperdevices.bsb.timer.main.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.flipperdevices.bsb.timer.main.model.TimerMainNavigationConfig
import com.flipperdevices.bsb.timer.setup.model.TimerState
import com.flipperdevices.core.di.AppGraph
import com.flipperdevices.ui.decompose.DecomposeComponent
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@Inject
class TimerMainDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
    private val mainScreenDecomposeComponent: (
        componentContext: ComponentContext,
        navigation: StackNavigation<TimerMainNavigationConfig>
    ) -> TimerMainScreenDecomposeComponentImpl,
    private val timerStopDecomposeComponent: (
        componentContext: ComponentContext,
        initialTimerState: TimerState,
    ) -> TimerStopScreenDecomposeComponentImpl
) : TimerMainDecomposeComponent<TimerMainNavigationConfig>(),
    ComponentContext by componentContext {
    override val stack = childStack(
        source = navigation,
        serializer = TimerMainNavigationConfig.serializer(),
        initialConfiguration = TimerMainNavigationConfig.Main,
        handleBackButton = true,
        childFactory = ::child,
    )

    private fun child(
        config: TimerMainNavigationConfig,
        componentContext: ComponentContext
    ): DecomposeComponent = when (config) {
        TimerMainNavigationConfig.Main -> mainScreenDecomposeComponent(componentContext, navigation)
        is TimerMainNavigationConfig.Timer -> timerStopDecomposeComponent(
            componentContext,
            config.timer
        )
    }

    @Inject
    @ContributesBinding(AppGraph::class, TimerMainDecomposeComponent.Factory::class)
    class Factory(
        private val factory: (
            componentContext: ComponentContext
        ) -> TimerMainDecomposeComponentImpl
    ) : TimerMainDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext
        ) = factory(componentContext)
    }
}