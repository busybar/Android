package com.flipperdevices.busybar.login.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
fun LogInScreenComposable(
    modifier: Modifier,
    onClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier.padding(top = 50.dp, bottom = 12.dp),
            painter = painterResource(Res.drawable.pic_busy_cloud),
            contentDescription = null
        )

        Box(
            contentAlignment = Alignment.Center
        ) {
            LogInBlockComposable(
                modifier = Modifier.padding(horizontal = 24.dp),
                onClick = onClick
            )
        }

        Column(Modifier.padding(top = 12.dp)) {
            OrLoginWithComposable(Modifier.padding(horizontal = 24.dp), onClick)
            SignUpBlockComposable(
                modifier = Modifier.padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = 81.dp,
                    bottom = 46.dp
                ).fillMaxWidth(),
                onClick = onClick
            )
        }
    }
}

@Preview
@Composable
private fun LogInScreenComposablePreview() {
    BusyBarThemeInternal {
        LogInScreenComposable(Modifier) {}
    }
}