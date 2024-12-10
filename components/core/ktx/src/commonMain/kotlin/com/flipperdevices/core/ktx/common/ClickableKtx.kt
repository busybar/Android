package com.flipperdevices.core.ktx.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

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