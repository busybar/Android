package com.flipperdevices.busybar.search.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.search_permission_btn
import busystatusbar.composeapp.generated.resources.search_permission_needed
import com.flipperdevices.busybar.core.ktx.clickableRipple
import com.flipperdevices.busybar.core.theme.LocalBusyBarFonts
import com.flipperdevices.busybar.core.theme.LocalPallet
import org.jetbrains.compose.resources.stringResource

@Composable
fun PermissionComposable(
    invalidate: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 14.dp),
            text = stringResource(Res.string.search_permission_needed),
            fontSize = 16.sp,
            lineHeight = 18.sp,
            fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
            fontWeight = FontWeight(500),
            color = LocalPallet.current.invert.black,
            textAlign = TextAlign.Center,
        )

        Text(
            modifier = Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(LocalPallet.current.brand.primary)
                .clickableRipple(onClick = invalidate)
                .padding(vertical = 14.dp),
            text = stringResource(Res.string.search_permission_btn),
            fontSize = 16.sp,
            fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
            fontWeight = FontWeight.W500,
            textAlign = TextAlign.Center,
            color = LocalPallet.current.onColor.white
        )
    }
}