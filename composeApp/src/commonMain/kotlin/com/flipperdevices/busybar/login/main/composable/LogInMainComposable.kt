package com.flipperdevices.busybar.login.main.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.login_main_title
import com.flipperdevices.busybar.login.common.composable.LogInAppBarComposable

@Composable
fun LogInMainComposable() {
    Column(
        Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        LogInAppBarComposable(Res.string.login_main_title)
    }
}