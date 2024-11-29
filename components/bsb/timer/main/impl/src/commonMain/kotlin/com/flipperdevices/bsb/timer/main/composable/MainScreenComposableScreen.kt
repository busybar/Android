package com.flipperdevices.bsb.timer.main.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.flipperdevices.bsb.core.theme.LocalPallet
import com.flipperdevices.bsb.timer.background.model.ControlledTimerState

@Composable
fun MainScreenComposableScreen(
    modifier: Modifier,
    setupComponent: @Composable (Modifier) -> Unit,
    onStart: () -> Unit
) {
    Column(
        modifier
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
                .background(LocalPallet.current.surface.primary)
                .statusBarsPadding()
                .padding(top = 16.dp)
        )
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
                .background(LocalPallet.current.surface.primary)
        ) {
            setupComponent(
                Modifier
                    .weight(1f)
            )
            StartButtonComposable(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 16.dp,
                        bottom = 128.dp,
                        start = 16.dp,
                        end = 16.dp
                    ),
                onClick = onStart
            )
        }
    }
}