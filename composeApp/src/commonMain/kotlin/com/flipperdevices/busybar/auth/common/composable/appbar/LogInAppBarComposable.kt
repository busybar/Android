package com.flipperdevices.busybar.auth.common.composable.appbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.ic_back
import com.flipperdevices.busybar.core.ktx.clickableRipple
import com.flipperdevices.busybar.core.theme.LocalBusyBarFonts
import com.flipperdevices.busybar.core.theme.LocalPallet
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun LogInAppBarComposable(
    text: StringResource,
    onBack: (() -> Unit)? = null
) {
    ThreeBlockAppBarComposable(
        startBlock = { modifier ->
            if (onBack != null) {
                Icon(
                    modifier = modifier
                        .clickableRipple(onClick = onBack, bounded = false)
                        .size(24.dp),
                    painter = painterResource(Res.drawable.ic_back),
                    contentDescription = null,
                    tint = LocalPallet.current.black.invert,
                )
            }
        },
        centerBlock = { modifier ->
            Text(
                modifier = modifier.padding(
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
    )
}