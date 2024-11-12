package com.flipperdevices.busybar.auth.login.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.login_signin_title
import com.flipperdevices.busybar.auth.common.composable.appbar.LogInAppBarComposable
import com.flipperdevices.busybar.auth.login.model.LoginState

@Composable
fun LoginPasswordScreenComposable(
    state: LoginState,
    email: String,
    onBack: () -> Unit,
    onLogin: (String) -> Unit
) {
    Column(
        Modifier.fillMaxSize().systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogInAppBarComposable(
            Res.string.login_signin_title,
            onBack = onBack
        )

        LoginPasswordComposable(
            modifier = Modifier.padding(
                vertical = 32.dp,
                horizontal = 16.dp
            ),
            email = email,
            onLogin = onLogin,
            state = state
        )
    }
}