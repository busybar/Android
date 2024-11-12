package com.flipperdevices.busybar.core.ktx

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

@Composable
fun Modifier.clickableRipple(
    bounded: Boolean = true,
    enabled: Boolean = true,
    onClick: () -> Unit
) = clickable(
    interactionSource = remember { MutableInteractionSource() },
    indication = ripple(bounded),
    onClick = onClick,
    enabled = enabled
)