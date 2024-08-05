package com.flipperdevices.busybar.login.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.pic_busy_cloud
import com.flipperdevices.busybar.core.theme.BusyBarThemeInternal
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LogInScreenComposable(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.padding(top = 50.dp),
            painter = painterResource(Res.drawable.pic_busy_cloud),
            contentDescription = null
        )

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            LogInBlockComposable(
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }

        OrLoginWithComposable(Modifier.padding(horizontal = 24.dp))
        SignUpBlockComposable(
            modifier = Modifier.padding(
                start = 24.dp,
                end = 24.dp,
                top = 81.dp,
                bottom = 46.dp
            ).fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun LogInScreenComposablePreview() {
    BusyBarThemeInternal {
        LogInScreenComposable(Modifier)
    }
}