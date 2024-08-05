package com.flipperdevices.busybar.login.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.login_btn
import busystatusbar.composeapp.generated.resources.login_forgot_password
import com.flipperdevices.busybar.core.theme.LocalBusyBarFonts
import com.flipperdevices.busybar.core.theme.LocalPallet
import org.jetbrains.compose.resources.stringResource

@Composable
fun LogInBlockComposable(modifier: Modifier) = Column(modifier) {
    EmailFieldComposable(
        modifier = Modifier
    )

    PasswordFieldComposable(
        modifier = Modifier.padding(top = 12.dp)
    )

    Text(
        modifier = Modifier.fillMaxWidth()
            .padding(vertical = 24.dp),
        text = stringResource(Res.string.login_forgot_password),
        textAlign = TextAlign.End,
        fontSize = 18.sp,
        fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
        fontWeight = FontWeight.W400,
        color = LocalPallet.current.invert.black
    )

    Text(
        modifier = Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(LocalPallet.current.brand.primary)
            .padding(16.dp),
        text = stringResource(Res.string.login_btn),
        fontSize = 16.sp,
        fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
        fontWeight = FontWeight.W500,
        textAlign = TextAlign.Center,
        color = LocalPallet.current.onColor.white
    )
}