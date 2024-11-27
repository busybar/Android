package com.flipperdevices.bsb.timer.main.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.arkivanov.essenty.lifecycle.coroutines.withLifecycle
import com.flipperdevices.bsb.timer.background.api.TimerApi
import com.flipperdevices.bsb.timer.background.model.ControlledTimerState
import com.flipperdevices.bsb.timer.background.model.TimerState
import com.flipperdevices.bsb.timer.main.model.TimerMainNavigationConfig
import com.flipperdevices.core.di.AppGraph
import com.flipperdevices.ui.decompose.DecomposeComponent
import com.flipperdevices.ui.decompose.DecomposeOnBackParameter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
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
        componentContext: ComponentContext
    ) -> TimerStopScreenDecomposeComponentImpl,
    timerApi: TimerApi
) : TimerMainDecomposeComponent<TimerMainNavigationConfig>(),
    ComponentContext by componentContext {

    init {
        timerApi.getState()
            .onEach { timerState ->
                withContext(Dispatchers.Main) {
                    navigation.replaceAll(timerState.getScreen())
                }
            }.launchIn(coroutineScope())
    }

    override val stack = childStack(
        source = navigation,
        serializer = TimerMainNavigationConfig.serializer(),
        initialConfiguration = timerApi.getState().value.getScreen(),
        handleBackButton = true,
        childFactory = ::child,
    )

    private fun child(
        config: TimerMainNavigationConfig,
        componentContext: ComponentContext
    ): DecomposeComponent = when (config) {
        TimerMainNavigationConfig.Main -> mainScreenDecomposeComponent(componentContext, navigation)
        TimerMainNavigationConfig.Timer -> timerStopDecomposeComponent(componentContext)
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

private fun ControlledTimerState?.getScreen(): TimerMainNavigationConfig {
    return if (this == null) {
        TimerMainNavigationConfig.Main
    } else {
        TimerMainNavigationConfig.Timer
    }
}