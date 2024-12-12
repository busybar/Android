package com.flipperdevices.bsb.auth.within.common.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.flipperdevices.bsb.core.theme.LocalPallet
import com.flipperdevices.core.ktx.common.clickableRipple
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun SignInWithButtonComposable(
    modifier: Modifier,
    icon: DrawableResource,
    onClick: () -> Unit,
    inProgress: Boolean
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(LocalPallet.current.transparent.blackInvert.quaternary)
            .clickableRipple(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = 12.dp,
            alignment = Alignment.CenterHorizontally
        )
    ) {
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(icon),
            contentDescription = null
        )

        if (inProgress) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(24.dp)
                    .padding(1.2.dp),
                color = LocalPallet.current.black.invert,
                backgroundColor = LocalPallet.current.transparent.blackInvert.secondary,
                strokeWidth = 2.dp
            )
        }
    }
}