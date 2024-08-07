package com.flipperdevices.busybar.search.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.device_btn_change_app
import busystatusbar.composeapp.generated.resources.ic_logo_horizontal
import busystatusbar.composeapp.generated.resources.ic_navigation
import busystatusbar.composeapp.generated.resources.search_demo_btn
import com.flipperdevices.busybar.core.ktx.clickableRipple
import com.flipperdevices.busybar.core.theme.LocalBusyBarFonts
import com.flipperdevices.busybar.core.theme.LocalPallet
import com.flipperdevices.busybar.search.model.SearchScreenState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SearchScreenComposable(
    modifier: Modifier,
    searchScreenState: SearchScreenState,
    onInvalidate: () -> Unit,
    onDemo: () -> Unit
) = Column(
    modifier = modifier.fillMaxHeight(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceBetween
) {
    Image(
        modifier = Modifier.padding(top = 44.dp),
        painter = painterResource(Res.drawable.ic_logo_horizontal),
        contentDescription = null
    )

    when (searchScreenState) {
        is SearchScreenState.FoundingDevice -> CircularProgressIndicator()
        is SearchScreenState.RequestPermissions -> PermissionComposable(
            modifier = Modifier.padding(horizontal = 24.dp),
            invalidate = onInvalidate
        )
    }


    Row(
        Modifier.fillMaxWidth()
            .clickableRipple(onClick = onDemo)
            .padding(vertical = 14.dp, horizontal = 12.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(Res.string.search_demo_btn),
            fontSize = 16.sp,
            fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
            fontWeight = FontWeight.W500,
            color = LocalPallet.current.brand.primary,
        )
        Icon(
            modifier = Modifier
                .padding(start = 2.dp)
                .size(14.dp),
            painter = painterResource(Res.drawable.ic_navigation),
            contentDescription = null,
            tint = LocalPallet.current.brand.primary
        )
    }
}