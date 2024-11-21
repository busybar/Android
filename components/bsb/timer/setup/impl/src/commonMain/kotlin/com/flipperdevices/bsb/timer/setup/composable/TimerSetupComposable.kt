package com.flipperdevices.bsb.timer.setup.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import busystatusbar.components.bsb.timer.setup.impl.generated.resources.Res
import busystatusbar.components.bsb.timer.setup.impl.generated.resources.ic_center_selector
import org.jetbrains.compose.resources.painterResource

@Composable
fun TimerSetupComposable(modifier: Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        NumberSelectorComposable(
            modifier = Modifier.fillMaxHeight(),
            count = 60,
            onSelect = {},
            initialNumber = 10
        )
        CenterSelectorComposable()
    }
}

@Composable
private fun CenterSelectorComposable() {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier.rotate(180f),
            painter = painterResource(Res.drawable.ic_center_selector),
            contentDescription = null,
        )
        Image(
            painter = painterResource(Res.drawable.ic_center_selector),
            contentDescription = null,
        )
    }
}