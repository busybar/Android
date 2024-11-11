package com.flipperdevices.busybar.auth.signup.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.login_signup_title
import com.flipperdevices.busybar.auth.common.composable.appbar.LogInAppBarComposable

@Composable
fun SignUpPasswordScreenComposable() {

    Column(
        Modifier.fillMaxSize().systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LogInAppBarComposable(Res.string.login_signup_title)
    }
}