package com.flipperdevices.busybar.auth.main.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.flipperdevices.busybar.core.ktx.clickableRipple
import com.flipperdevices.busybar.core.theme.LocalPallet
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun SignInWithButtonComposable(
    modifier: Modifier,
    icon: DrawableResource,
    onClick: () -> Unit
) {
    Image(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(LocalPallet.current.transparent.blackInvert.quaternary)
            .clickableRipple(onClick = onClick)
            .padding(vertical = 12.dp)
            .size(24.dp),
        painter = painterResource(icon),
        contentDescription = null
    )
}