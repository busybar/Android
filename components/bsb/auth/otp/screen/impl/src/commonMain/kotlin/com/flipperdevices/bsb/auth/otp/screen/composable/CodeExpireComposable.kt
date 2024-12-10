package com.flipperdevices.bsb.auth.otp.screen.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busystatusbar.components.bsb.auth.otp.screen.impl.generated.resources.Res
import busystatusbar.components.bsb.auth.otp.screen.impl.generated.resources.ic_error_request
import busystatusbar.components.bsb.auth.otp.screen.impl.generated.resources.login_otp_screen_code_resend
import com.flipperdevices.bsb.auth.common.composable.BusyBarButtonComposable
import com.flipperdevices.bsb.auth.otp.screen.model.InternalAuthOtpType
import com.flipperdevices.bsb.core.theme.LocalBusyBarFonts
import com.flipperdevices.bsb.core.theme.LocalPallet
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun CodeExpireComposable(
    modifier: Modifier,
    otpType: InternalAuthOtpType,
    onResend: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(vertical = 26.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(40.dp),
            painter = painterResource(Res.drawable.ic_error_request),
            contentDescription = null,
            tint = LocalPallet.current.danger.primary
        )
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(otpType.textCodeError),
            fontSize = 20.sp,
            color = LocalPallet.current.danger.primary,
            fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
            fontWeight = FontWeight.W500,
            textAlign = TextAlign.Center
        )
    }

    BusyBarButtonComposable(
        modifier = Modifier
            .padding(vertical = 32.dp)
            .fillMaxWidth(),
        text = Res.string.login_otp_screen_code_resend,
        onClick = onResend,
        inProgress = false
    )
}