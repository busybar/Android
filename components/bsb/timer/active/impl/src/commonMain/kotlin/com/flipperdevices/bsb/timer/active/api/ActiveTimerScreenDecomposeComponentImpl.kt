package com.flipperdevices.bsb.timer.active.api

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.bsb.core.theme.LocalPallet
import com.flipperdevices.bsb.preference.api.ThemeStatusBarIconStyleProvider
import com.flipperdevices.bsb.timer.active.composable.StopButtonComposable
import com.flipperdevices.bsb.timer.active.composable.TimerContainerComposable
import com.flipperdevices.bsb.timer.background.api.TimerApi
import com.flipperdevices.core.di.AppGraph
import com.flipperdevices.ui.decompose.statusbar.StatusBarIconStyleProvider
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@Inject
class ActiveTimerScreenDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
    iconStyleProvider: ThemeStatusBarIconStyleProvider,
    private val timerApi: TimerApi
) : ActiveTimerScreenDecomposeComponent(componentContext), StatusBarIconStyleProvider by iconStyleProvider {
    @Composable
    override fun Render(modifier: Modifier) {
        Column(
            modifier
                .background(LocalPallet.current.black.invert)
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomEnd = 16.dp,
                            bottomStart = 16.dp
                        )
                    )
                    .background(Color(0xFFF42323))
                    .statusBarsPadding()
                    .padding(top = 16.dp)
            )
            val state by timerApi.getState().collectAsState()
            val localState = state ?: return
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(top = 2.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 16.dp,
                            topEnd = 16.dp,
                            bottomEnd = 0.dp,
                            bottomStart = 0.dp
                        )
                    )
                    .background(Color(0xFFF42323))
            ) {

                TimerContainerComposable(
                    modifier = Modifier.weight(1f),
                    timerState = localState,
                    onAction = timerApi::onAction
                )

                StopButtonComposable(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 16.dp,
                            bottom = 128.dp,
                            start = 16.dp,
                            end = 16.dp
                        ),
                    onClick = timerApi::stopTimer
                )
            }
        }
    }

    @Inject
    @ContributesBinding(AppGraph::class, ActiveTimerScreenDecomposeComponent.Factory::class)
    class Factory(
        private val factory: (
            componentContext: ComponentContext
        ) -> ActiveTimerScreenDecomposeComponentImpl
    ) : ActiveTimerScreenDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext
        ) = factory(componentContext)
    }
}