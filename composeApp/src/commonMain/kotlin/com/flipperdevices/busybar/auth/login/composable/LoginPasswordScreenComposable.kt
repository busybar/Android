package com.flipperdevices.busybar.auth.login.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.login_main_footer
import busystatusbar.composeapp.generated.resources.login_main_title
import com.flipperdevices.busybar.auth.common.composable.LogInAppBarComposable
import com.flipperdevices.busybar.auth.main.composable.AuthMainComposable
import com.flipperdevices.busybar.auth.main.composable.markdownColor
import com.flipperdevices.busybar.auth.main.composable.markdownTypography
import com.mikepenz.markdown.compose.Markdown
import com.mikepenz.markdown.compose.components.markdownComponents
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginPasswordScreenComposable() {

    Column(
        Modifier.fillMaxSize().systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LogInAppBarComposable(Res.string.login_main_title)


        Markdown(
            modifier = Modifier,
            content = stringResource(Res.string.login_main_footer),
            components = markdownComponents(),
            colors = markdownColor(),
            typography = markdownTypography()
        )
    }
}