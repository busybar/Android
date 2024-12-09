package com.flipperdevices.bsb.auth.otp.screen.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import busystatusbar.components.bsb.auth.otp.screen.impl.generated.resources.Res
import busystatusbar.components.bsb.auth.otp.screen.impl.generated.resources.pic_email_verification
import com.flipperdevices.bsb.auth.otp.screen.model.AuthOtpType
import com.flipperdevices.bsb.auth.otp.screen.model.InternalAuthOtpType
import com.flipperdevices.bsb.core.theme.LocalBusyBarFonts
import com.flipperdevices.bsb.core.theme.LocalPallet
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AuthOtpScreenHeaderComposable(
    modifier: Modifier,
    otpType: InternalAuthOtpType
) {
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.width(156.dp),
            painter = painterResource(Res.drawable.pic_email_verification),
            contentDescription = stringResource(otpType.textDesc),
            contentScale = ContentScale.FillWidth
        )

        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(otpType.textDesc),
            textAlign = TextAlign.Center,
            fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
            fontWeight = FontWeight.W400,
            color = LocalPallet.current.black.invert
        )
        Text(
            text = otpType.email,
            textAlign = TextAlign.Center,
            fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
            fontWeight = FontWeight.W600,
            color = LocalPallet.current.black.invert
        )
    }
}