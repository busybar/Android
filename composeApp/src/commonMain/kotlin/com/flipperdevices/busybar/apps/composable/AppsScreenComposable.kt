package com.flipperdevices.busybar.apps.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.ic_back
import busystatusbar.composeapp.generated.resources.ic_navigation
import com.flipperdevices.busybar.apps.composable.apps.APPS
import com.flipperdevices.busybar.apps.composable.apps.BusyBarApp
import com.flipperdevices.busybar.apps.composable.apps.BusyBarAppComposable
import com.flipperdevices.busybar.core.ktx.clickableRipple
import com.flipperdevices.busybar.core.theme.LocalPallet
import org.jetbrains.compose.resources.painterResource

@Composable
fun AppsScreenComposable(
    modifier: Modifier,
    onBack: () -> Unit,
    onAppClick: (BusyBarApp) -> Unit,
) = Column(modifier) {
    AppsScreenComposableBar(onBack)

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        APPS.forEachIndexed { index, app ->
            BusyBarAppComposable(
                modifier = Modifier.clickableRipple {
                    onAppClick(app)
                },
                app = app
            )
            if (index != APPS.lastIndex) {
                Box(
                    Modifier
                        .padding(vertical = 24.dp)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(LocalPallet.current.neutral.quinary)
                )
            }
        }
    }
}

@Composable
fun AppsScreenComposableBar(onBack: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().height(30.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.padding(start = 14.dp)
                .size(24.dp)
                .clickableRipple(onClick = onBack),
            painter = painterResource(Res.drawable.ic_back),
            contentDescription = null,
            tint = LocalPallet.current.neutral.tertiary
        )
    }
}