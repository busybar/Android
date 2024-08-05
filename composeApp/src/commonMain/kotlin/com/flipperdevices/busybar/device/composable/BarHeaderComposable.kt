package com.flipperdevices.busybar.device.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.device_btn_change_app
import busystatusbar.composeapp.generated.resources.ic_navigation
import busystatusbar.composeapp.generated.resources.pic_device_main
import com.flipperdevices.busybar.core.theme.LocalBusyBarFonts
import com.flipperdevices.busybar.core.theme.LocalPallet
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun BarHeaderComposable(
    modifier: Modifier = Modifier
) = Column(modifier = modifier) {
    Image(
        modifier = Modifier
            .fillMaxWidth(),
        contentScale = ContentScale.FillWidth,
        painter = painterResource(Res.drawable.pic_device_main),
        contentDescription = null
    )

    Row(
        Modifier.fillMaxWidth()
            .padding(top = 18.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(LocalPallet.current.brand.primary)
            .padding(vertical = 14.dp, horizontal = 12.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(Res.string.device_btn_change_app),
            fontSize = 16.sp,
            fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
            fontWeight = FontWeight.W500,
            color = LocalPallet.current.onColor.white,
        )
        Icon(
            modifier = Modifier
                .padding(start = 2.dp)
                .size(14.dp),
            painter = painterResource(Res.drawable.ic_navigation),
            contentDescription = null,
            tint = LocalPallet.current.onColor.white
        )
    }
}