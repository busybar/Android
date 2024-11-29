package com.flipperdevices.bsb.timer.main.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import busystatusbar.components.bsb.timer.main.impl.generated.resources.Res
import busystatusbar.components.bsb.timer.main.impl.generated.resources.ic_settings
import com.flipperdevices.bsb.core.theme.LocalPallet
import com.flipperdevices.core.ktx.jre.clickableRipple
import org.jetbrains.compose.resources.painterResource

@Composable
fun MainScreenComposableScreen(
    modifier: Modifier,
    setupComponent: @Composable (Modifier) -> Unit,
    onStart: () -> Unit,
    onOpenSettings: () -> Unit
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
        Box(
            Modifier
                .padding(top = 2.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(LocalPallet.current.surface.primary),
            contentAlignment = Alignment.TopEnd
        ) {
            Box(
                Modifier.padding(end = 16.dp)
                    .clickableRipple(onClick = onOpenSettings)
            ) {
                Icon(
                    modifier = Modifier
                        .padding(24.dp)
                        .size(32.dp),
                    painter = painterResource(Res.drawable.ic_settings),
                    tint = LocalPallet.current.black.invert,
                    contentDescription = null
                )
            }
        }
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