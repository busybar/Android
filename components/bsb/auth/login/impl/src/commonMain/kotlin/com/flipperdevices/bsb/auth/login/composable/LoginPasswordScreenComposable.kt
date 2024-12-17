package com.flipperdevices.bsb.auth.login.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import busystatusbar.components.bsb.auth.login.impl.generated.resources.Res
import busystatusbar.components.bsb.auth.login.impl.generated.resources.login_signin_title
import com.flipperdevices.bsb.auth.common.composable.appbar.LogInAppBarComposable
import com.flipperdevices.bsb.auth.login.model.LoginState

@Composable
fun LoginPasswordScreenComposable(
    state: LoginState,
    email: String,
    preFilledPassword: String?,
    onBack: () -> Unit,
    onLogin: (String) -> Unit,
    onForgotPassword: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier.fillMaxSize()
            .navigationBarsPadding(),
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
            state = state,
            onForgotPassword = onForgotPassword,
            preFilledPassword = preFilledPassword
        )
    }
}
