package com.flipperdevices.core.ktx.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import io.github.fornewid.placeholder.foundation.PlaceholderHighlight
import io.github.fornewid.placeholder.foundation.placeholder
import io.github.fornewid.placeholder.foundation.shimmer

private val placeholderColor = Color(color = 0x2A2A2A)

@Composable
@Suppress("ModifierComposable")
fun Modifier.placeholder(
    visible: Boolean,
    shape: Shape = RectangleShape
) = placeholder(
    visible = visible,
    color = placeholderColor.copy(alpha = 0.2f),
    shape = shape,
    highlight = PlaceholderHighlight.shimmer(
        highlightColor = placeholderColor
    )
)
