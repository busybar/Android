package com.flipperdevices.bsb.auth.otp.screen.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busystatusbar.components.bsb.auth.otp.screen.impl.generated.resources.Res
import busystatusbar.components.bsb.auth.otp.screen.impl.generated.resources.login_otp_screen_code_expires
import busystatusbar.components.bsb.auth.otp.screen.impl.generated.resources.login_otp_screen_code_incorrect
import com.flipperdevices.bsb.auth.otp.element.model.OtpElementState
import com.flipperdevices.bsb.auth.otp.screen.model.AuthOtpScreenState
import com.flipperdevices.bsb.auth.otp.screen.model.InternalAuthOtpType
import com.flipperdevices.bsb.core.theme.LocalBusyBarFonts
import com.flipperdevices.bsb.core.theme.LocalPallet
import com.flipperdevices.core.data.timer.TimerState
import org.jetbrains.compose.resources.stringResource

@Composable
fun OtpCodeFieldComposable(
    modifier: Modifier,
    otpCodeFieldComposable: @Composable (Modifier, OtpElementState) -> Unit,
    otpScreenState: AuthOtpScreenState,
    timerState: TimerState,
    otpType: InternalAuthOtpType
) {
    Column(modifier) {
        Row(Modifier.padding(bottom = 8.dp)) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(otpType.textCodeTitle),
                color = LocalPallet.current.black.invert,
                fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
                fontWeight = FontWeight.W500,
                fontSize = 12.sp
            )
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = stringResource(
                    Res.string.login_otp_screen_code_expires,
                    timerState.toHumanReadableString()
                ),
                color = LocalPallet.current.black.invert,
                fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
                fontWeight = FontWeight.W500,
                fontSize = 12.sp
            )
        }
        otpCodeFieldComposable(
            Modifier, when (otpScreenState) {
                AuthOtpScreenState.ResetPasswordInProgress,
                AuthOtpScreenState.CheckCodeInProgress -> OtpElementState.DISABLED

                AuthOtpScreenState.ExpiryVerificationCode -> OtpElementState.WAITING_FOR_INPUT
                is AuthOtpScreenState.WaitingForInput -> if (otpScreenState.wrongCodeInvalid) {
                    OtpElementState.WAITING_FOR_INPUT
                } else {
                    OtpElementState.ERROR
                }
            }
        )

        when (otpScreenState) {
            AuthOtpScreenState.CheckCodeInProgress,
            AuthOtpScreenState.ExpiryVerificationCode,
            AuthOtpScreenState.ResetPasswordInProgress -> {
            }

            is AuthOtpScreenState.WaitingForInput -> if (otpScreenState.wrongCodeInvalid) {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = stringResource(Res.string.login_otp_screen_code_incorrect),
                    color = LocalPallet.current.danger.primary,
                    fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
                    fontWeight = FontWeight.W500,
                    fontSize = 12.sp
                )
            }
        }
    }
}