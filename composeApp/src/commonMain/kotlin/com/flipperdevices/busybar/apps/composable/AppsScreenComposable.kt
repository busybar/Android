package com.flipperdevices.busybar.apps.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flipperdevices.busybar.apps.composable.apps.APPS
import com.flipperdevices.busybar.apps.composable.apps.BusyBarAppComposable
import com.flipperdevices.busybar.core.ktx.clickableRipple
import com.flipperdevices.busybar.core.theme.LocalPallet

@Composable
fun AppsScreenComposable(
    modifier: Modifier,
    onClick: () -> Unit
) = Column(modifier) {
    CreateStatusComposable(Modifier.clickableRipple(onClick = onClick))

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        APPS.forEachIndexed { index, app ->
            BusyBarAppComposable(app = app, onClick = onClick)
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
