package com.flipperdevices.busybar.auth.main.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.login_main_or_line
import com.flipperdevices.busybar.core.theme.LocalBusyBarFonts
import com.flipperdevices.busybar.core.theme.LocalPallet
import org.jetbrains.compose.resources.stringResource

@Composable
fun OrLineComposable(
    modifier: Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier.fillMaxWidth()
                .height(1.dp)
                .background(LocalPallet.current.neutral.quinary)
        )

        Text(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .padding(horizontal = 12.dp),
            text = stringResource(Res.string.login_main_or_line),
            color = LocalPallet.current.neutral.tertiary,
            fontSize = 14.sp,
            fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
            fontWeight = FontWeight.W400,
        )
    }
}