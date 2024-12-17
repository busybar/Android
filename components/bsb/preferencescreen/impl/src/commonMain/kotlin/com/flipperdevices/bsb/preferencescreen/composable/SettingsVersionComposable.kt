package com.flipperdevices.bsb.preferencescreen.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busystatusbar.components.bsb.preferencescreen.impl.generated.resources.Res
import busystatusbar.components.bsb.preferencescreen.impl.generated.resources.ic_busy_logo
import busystatusbar.components.bsb.preferencescreen.impl.generated.resources.ic_busy_logo_dark
import busystatusbar.components.bsb.preferencescreen.impl.generated.resources.preference_version
import com.flipperdevices.bsb.core.theme.LocalBusyBarFonts
import com.flipperdevices.bsb.core.theme.LocalPallet
import com.flipperdevices.core.buildkonfig.BuildKonfig
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

private const val EXPERT_MODE_CLICK_COUNT = 10

@Composable
fun SettingsVersionComposable(
    onActivateExpertMode: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var howMuchClick by remember { mutableIntStateOf(0) }

    Column(
        modifier
            .clickable(indication = null, interactionSource = null) {
                howMuchClick++
                if (howMuchClick > EXPERT_MODE_CLICK_COUNT) {
                    onActivateExpertMode()
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(9.dp)
    ) {
        Image(
            modifier = Modifier.width(75.dp),
            painter = painterResource(
                if (MaterialTheme.colors.isLight) {
                    Res.drawable.ic_busy_logo
                } else {
                    Res.drawable.ic_busy_logo_dark
                }
            ),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        Text(
            text = stringResource(Res.string.preference_version, BuildKonfig.VERSION),
            color = LocalPallet.current.neutral.tertiary,
            fontSize = 16.sp,
            fontFamily = LocalBusyBarFonts.current.pragmatica,
            fontWeight = FontWeight.W500
        )
    }
}
