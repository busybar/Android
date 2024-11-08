package com.flipperdevices.busybar.login.common.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flipperdevices.busybar.core.ktx.clickableRipple
import com.flipperdevices.busybar.core.theme.LocalBusyBarFonts
import com.flipperdevices.busybar.core.theme.LocalPallet
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun BusyBarButtonComposable(
    text: StringResource,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.clip(RoundedCornerShape(8.dp))
            .background(LocalPallet.current.brand.primary)
            .clickableRipple(onClick = onClick)
            .padding(12.dp),
        text = stringResource(text),
        fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
        fontSize = 16.sp,
        fontWeight = FontWeight.W500,
        textAlign = TextAlign.Center,
        color = LocalPallet.current.white.onColor
    )
}