package com.flipperdevices.busybar.auth.common.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flipperdevices.busybar.core.theme.LocalBusyBarFonts
import com.flipperdevices.busybar.core.theme.LocalPallet
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun LogInAppBarComposable(
    text: StringResource
) {
    Text(
        modifier = Modifier.padding(
            horizontal = 16.dp,
            vertical = 12.dp
        ).fillMaxWidth(),
        text = stringResource(text),
        fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
        fontWeight = FontWeight.W500,
        color = LocalPallet.current.black.invert,
        textAlign = TextAlign.Center,
        fontSize = 24.sp
    )
}