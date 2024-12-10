package com.flipperdevices.bsb.auth.otp.screen.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flipperdevices.bsb.auth.common.composable.BusyBarButtonComposable
import com.flipperdevices.bsb.auth.common.composable.appbar.LogInAppBarComposable
import com.flipperdevices.bsb.auth.otp.screen.model.AuthOtpScreenState
import com.flipperdevices.bsb.auth.otp.screen.model.InternalAuthOtpType
import com.flipperdevices.bsb.core.markdown.ComposableMarkdown
import com.flipperdevices.bsb.core.markdown.markdownColor
import com.flipperdevices.bsb.core.markdown.markdownTypography
import com.flipperdevices.bsb.core.theme.LocalBusyBarFonts
import com.flipperdevices.bsb.core.theme.LocalPallet
import org.jetbrains.compose.resources.stringResource

@Composable
fun AuthOtpScreenComposable(
    modifier: Modifier,
    otpType: InternalAuthOtpType,
    onBack: () -> Unit,
    otpCodeFieldComposable: @Composable (Modifier) -> Unit,
    onConfirm: () -> Unit,
    onResend: () -> Unit,
    authOtpScreenState: AuthOtpScreenState
) {
    Column(modifier) {
        LogInAppBarComposable(
            text = otpType.textTitle, onBack = onBack
        )
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            AuthOtpScreenHeaderComposable(modifier = Modifier, otpType)

            when (authOtpScreenState) {
                AuthOtpScreenState.CheckCodeInProgress,
                AuthOtpScreenState.ResetPasswordInProgress,
                is AuthOtpScreenState.WaitingForInput -> {
                    otpCodeFieldComposable(Modifier)

                    BusyBarButtonComposable(
                        modifier = Modifier
                            .padding(vertical = 32.dp)
                            .fillMaxWidth(),
                        text = otpType.textCodeBtn,
                        onClick = onConfirm,
                        inProgress = when (authOtpScreenState) {
                            AuthOtpScreenState.ResetPasswordInProgress,
                            AuthOtpScreenState.ExpiryVerificationCode,
                            is AuthOtpScreenState.WaitingForInput -> false

                            AuthOtpScreenState.CheckCodeInProgress -> true
                        },
                        disabled = authOtpScreenState.inProgress
                    )
                }

                AuthOtpScreenState.ExpiryVerificationCode -> {
                    CodeExpireComposable(
                        Modifier.padding(vertical = 32.dp),
                        otpType = otpType,
                        onResend = onResend
                    )
                }
            }

            ComposableMarkdown(
                content = stringResource(otpType.textCodeDescMarkdown),
                typography = markdownTypography(
                    TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
                    )
                ),
                colors = markdownColor(LocalPallet.current.neutral.secondary)
            )

            ResendEmailComposable(
                Modifier
                    .padding(top = 32.dp),
                onClick = onResend,
                authOtpScreenState = authOtpScreenState
            )
        }
    }
}