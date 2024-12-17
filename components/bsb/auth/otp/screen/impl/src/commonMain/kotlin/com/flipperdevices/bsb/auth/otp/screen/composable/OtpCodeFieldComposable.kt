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
import busystatusbar.components.bsb.auth.otp.screen.impl.generated.resources.login_otp_screen_verify_error_general
import com.flipperdevices.bsb.auth.otp.element.model.OtpElementState
import com.flipperdevices.bsb.auth.otp.screen.model.AuthOtpExpiryState
import com.flipperdevices.bsb.auth.otp.screen.model.AuthOtpScreenState
import com.flipperdevices.bsb.auth.otp.screen.model.InternalAuthOtpType
import com.flipperdevices.bsb.core.theme.LocalBusyBarFonts
import com.flipperdevices.bsb.core.theme.LocalPallet
import org.jetbrains.compose.resources.stringResource

@Composable
fun OtpCodeFieldComposable(
    modifier: Modifier,
    otpCodeFieldComposable: @Composable (Modifier, OtpElementState) -> Unit,
    otpScreenState: AuthOtpScreenState,
    otpType: InternalAuthOtpType,
    expiryState: AuthOtpExpiryState
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

            ExpiryTimerTextComposable(
                modifier = Modifier.padding(start = 16.dp),
                expiryState = expiryState
            )
        }
        otpCodeFieldComposable(
            Modifier, when (otpScreenState) {
                is AuthOtpScreenState.RequestEmailInProgress,
                AuthOtpScreenState.CheckCodeInProgress -> OtpElementState.IN_PROGRESS

                AuthOtpScreenState.ExpiryVerificationCode -> OtpElementState.WAITING_FOR_INPUT
                is AuthOtpScreenState.WaitingForInput -> if (otpScreenState.wrongCodeInvalid) {
                    OtpElementState.ERROR
                } else {
                    OtpElementState.WAITING_FOR_INPUT
                }
            }
        )

        when (otpScreenState) {
            AuthOtpScreenState.CheckCodeInProgress,
            AuthOtpScreenState.ExpiryVerificationCode,
            is AuthOtpScreenState.RequestEmailInProgress -> {
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

@Composable
private fun ExpiryTimerTextComposable(
    modifier: Modifier,
    expiryState: AuthOtpExpiryState
) {
    when (expiryState) {
        AuthOtpExpiryState.Empty -> {}
        AuthOtpExpiryState.Error -> Text(
            modifier = modifier,
            text = stringResource(Res.string.login_otp_screen_verify_error_general),
            color = LocalPallet.current.danger.primary,
            fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
            fontWeight = FontWeight.W500,
            fontSize = 12.sp
        )

        is AuthOtpExpiryState.Ready -> Text(
            modifier = modifier,
            text = stringResource(
                Res.string.login_otp_screen_code_expires,
                expiryState.timerState.toHumanReadableString()
            ),
            color = LocalPallet.current.black.invert,
            fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
            fontWeight = FontWeight.W500,
            fontSize = 12.sp
        )
    }
}