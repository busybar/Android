package com.flipperdevices.bsb.auth.otp.screen.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busystatusbar.components.bsb.auth.otp.screen.impl.generated.resources.Res
import busystatusbar.components.bsb.auth.otp.screen.impl.generated.resources.login_otp_screen_code_resend
import com.flipperdevices.bsb.auth.common.composable.UiConstants.ALPHA_DISABLED
import com.flipperdevices.bsb.auth.otp.screen.model.AuthOtpScreenState
import com.flipperdevices.bsb.core.theme.LocalBusyBarFonts
import com.flipperdevices.bsb.core.theme.LocalPallet
import com.flipperdevices.core.ktx.common.clickableRipple
import org.jetbrains.compose.resources.stringResource

@Composable
fun ResendEmailComposable(
    modifier: Modifier,
    onClick: () -> Unit,
    authOtpScreenState: AuthOtpScreenState,
) {
    val inProgress = authOtpScreenState.inProgress
    Column(
        modifier
            .fillMaxWidth()
            .graphicsLayer {
                if (inProgress) {
                    this.alpha = ALPHA_DISABLED
                }
            },
        horizontalAlignment = Alignment.End
    ) {
        Text(
            modifier = Modifier.padding(12.dp)
                .clickableRipple(onClick = {
                    if (inProgress.not()) {
                        onClick()
                    }
                }),
            text = stringResource(Res.string.login_otp_screen_code_resend),
            fontSize = 16.sp,
            fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
            fontWeight = FontWeight.W500,
            color = LocalPallet.current.brand.primary,
            textAlign = TextAlign.End
        )

        when (authOtpScreenState) {
            AuthOtpScreenState.CheckCodeInProgress,
            is AuthOtpScreenState.WaitingForInput,
            AuthOtpScreenState.ExpiryVerificationCode -> {
            }

            AuthOtpScreenState.ResetPasswordInProgress -> {
                CircularProgressIndicator(
                    Modifier.size(22.dp)
                        .padding(1.2.dp)
                        .padding(start = 12.dp),
                    color = LocalPallet.current.brand.primary,
                    backgroundColor = LocalPallet.current.brand.secondary,
                    strokeWidth = 1.dp
                )
            }
        }
    }
}