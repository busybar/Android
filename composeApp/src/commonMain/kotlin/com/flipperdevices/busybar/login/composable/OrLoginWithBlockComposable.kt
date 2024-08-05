package com.flipperdevices.busybar.login.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.ic_google
import busystatusbar.composeapp.generated.resources.login_or_login_with
import com.flipperdevices.busybar.core.theme.LocalBusyBarFonts
import com.flipperdevices.busybar.core.theme.LocalPallet
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun OrLoginWithComposable(modifier: Modifier) = Column(modifier) {
    HorizontalLineWithText()
    SignInGoogle(Modifier.padding(top = 18.dp))
}

@Composable
private fun SignInGoogle(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(size = 4.dp))
            .background(LocalPallet.current.transparent.black.tertiary),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.padding(12.dp),
            painter = painterResource(Res.drawable.ic_google),
            contentDescription = null
        )
    }
}

@Composable
private fun HorizontalLineWithText(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier.fillMaxWidth()
                .height(1.dp)
                .background(LocalPallet.current.neutral.quinary)
        )

        Text(
            modifier = Modifier
                .background(
                    LocalPallet.current.background.primary
                )
                .padding(horizontal = 12.dp),
            text = stringResource(Res.string.login_or_login_with),
            fontSize = 18.sp,
            fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
            fontWeight = FontWeight.W400,
            color = LocalPallet.current.neutral.tertiary,
            letterSpacing = 0.54.sp,
        )
    }
}