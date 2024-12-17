package com.flipperdevices.bsb.auth.main.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busystatusbar.components.bsb.auth.main.impl.generated.resources.Res
import busystatusbar.components.bsb.auth.main.impl.generated.resources.login_main_footer
import busystatusbar.components.bsb.auth.main.impl.generated.resources.login_main_title
import com.flipperdevices.bsb.auth.common.composable.appbar.LogInAppBarComposable
import com.flipperdevices.bsb.auth.main.model.AuthMainState
import com.flipperdevices.bsb.core.markdown.ComposableMarkdown
import org.jetbrains.compose.resources.stringResource

@Composable
fun AuthMainComposableScreen(
    state: AuthMainState,
    onLogin: (String) -> Unit,
    onPrefillPassword: (String) -> Unit,
    signInWith: @Composable (Modifier) -> Unit
) {
    Column(
        Modifier.fillMaxSize()
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogInAppBarComposable(Res.string.login_main_title)

        AuthMainComposable(
            modifier = Modifier.padding(top = 32.dp)
                .weight(1f),
            state = state,
            onLogin = onLogin,
            signInWith = signInWith,
            onPrefillPassword = onPrefillPassword
        )

        ComposableMarkdown(
            content = stringResource(Res.string.login_main_footer)
        )
    }
}
