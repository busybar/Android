package com.flipperdevices.bsb.timer.main.api

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.flipperdevices.bsb.core.theme.LocalPallet
import com.flipperdevices.bsb.timer.main.composable.BusyButtonComposable
import com.flipperdevices.bsb.timer.main.viewmodel.BusyTimerViewModel
import com.flipperdevices.bsb.timer.setup.api.TimerSetupScreenDecomposeComponent
import com.flipperdevices.bsb.timer.setup.model.TimerState
import com.flipperdevices.core.ui.lifecycle.viewModelWithFactoryWithoutRemember
import com.flipperdevices.ui.decompose.ScreenDecomposeComponent
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class TimerStopScreenDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
    @Assisted initialTimerState: TimerState,
    private val busyTimerViewModel: (TimerState) -> BusyTimerViewModel
) : ScreenDecomposeComponent(componentContext) {
    val viewModel = viewModelWithFactoryWithoutRemember(initialTimerState) {
        busyTimerViewModel(initialTimerState)
    }

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
            val state by viewModel.getState().collectAsState()
            Column(
                Modifier
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
                Text(state.toString())
            }
        }
    }
}