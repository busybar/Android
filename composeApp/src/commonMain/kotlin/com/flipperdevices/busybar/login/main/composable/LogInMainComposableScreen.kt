package com.flipperdevices.busybar.login.main.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.login_main_footer
import busystatusbar.composeapp.generated.resources.login_main_title
import com.flipperdevices.busybar.core.theme.LocalBusyBarFonts
import com.flipperdevices.busybar.core.theme.LocalPallet
import com.flipperdevices.busybar.login.common.composable.LogInAppBarComposable
import com.mikepenz.markdown.compose.Markdown
import com.mikepenz.markdown.compose.components.markdownComponents
import com.mikepenz.markdown.model.DefaultMarkdownColors
import com.mikepenz.markdown.model.DefaultMarkdownTypography
import com.mikepenz.markdown.model.MarkdownColors
import com.mikepenz.markdown.model.MarkdownTypography
import org.jetbrains.compose.resources.stringResource

@Composable
fun LogInMainComposableScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogInAppBarComposable(Res.string.login_main_title)

        LogInMainComposable(
            modifier = Modifier.weight(1f)
        )

        Markdown(
            stringResource(Res.string.login_main_footer),
            components = markdownComponents(),
            colors = markdownColor(),
            typography = markdownTypography()
        )
    }
}

@Composable
fun markdownColor(
    defaultColor: Color = LocalPallet.current.neutral.tertiary
): MarkdownColors = DefaultMarkdownColors(
    text = defaultColor,
    codeText = defaultColor,
    inlineCodeText = defaultColor,
    linkText = LocalPallet.current.bluetooth.primary,
    codeBackground = defaultColor,
    inlineCodeBackground = defaultColor,
    dividerColor = defaultColor
)

@Composable
fun markdownTypography(
    defaultTypography: TextStyle = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.W500,
        fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
    )
): MarkdownTypography = DefaultMarkdownTypography(
    h1 = defaultTypography,
    h2 = defaultTypography,
    h3 = defaultTypography,
    h4 = defaultTypography,
    h5 = defaultTypography,
    h6 = defaultTypography,
    text = defaultTypography,
    code = defaultTypography,
    inlineCode = defaultTypography,
    quote = defaultTypography,
    paragraph = defaultTypography,
    ordered = defaultTypography,
    bullet = defaultTypography,
    list = defaultTypography,
    link = defaultTypography.copy(
        textDecoration = TextDecoration.Underline,
    )
)