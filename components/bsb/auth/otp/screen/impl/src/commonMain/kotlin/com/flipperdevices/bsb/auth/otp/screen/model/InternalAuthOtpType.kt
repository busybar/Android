package com.flipperdevices.bsb.auth.otp.screen.model

import androidx.compose.runtime.Stable
import busystatusbar.components.bsb.auth.otp.screen.impl.generated.resources.Res
import busystatusbar.components.bsb.auth.otp.screen.impl.generated.resources.login_otp_screen_forgot_code_btn
import busystatusbar.components.bsb.auth.otp.screen.impl.generated.resources.login_otp_screen_forgot_code_desc_markdown
import busystatusbar.components.bsb.auth.otp.screen.impl.generated.resources.login_otp_screen_forgot_code_error
import busystatusbar.components.bsb.auth.otp.screen.impl.generated.resources.login_otp_screen_forgot_code_title
import busystatusbar.components.bsb.auth.otp.screen.impl.generated.resources.login_otp_screen_forgot_desc
import busystatusbar.components.bsb.auth.otp.screen.impl.generated.resources.login_otp_screen_forgot_title
import busystatusbar.components.bsb.auth.otp.screen.impl.generated.resources.login_otp_screen_verify_code_btn
import busystatusbar.components.bsb.auth.otp.screen.impl.generated.resources.login_otp_screen_verify_code_desc_markdown
import busystatusbar.components.bsb.auth.otp.screen.impl.generated.resources.login_otp_screen_verify_code_error
import busystatusbar.components.bsb.auth.otp.screen.impl.generated.resources.login_otp_screen_verify_code_title
import busystatusbar.components.bsb.auth.otp.screen.impl.generated.resources.login_otp_screen_verify_desc
import busystatusbar.components.bsb.auth.otp.screen.impl.generated.resources.login_otp_screen_verify_title
import com.flipperdevices.bsb.cloud.model.BSBEmailVerificationType
import org.jetbrains.compose.resources.StringResource

@Stable
sealed interface InternalAuthOtpType {
    val email: String
    val textTitle: StringResource
    val textDesc: StringResource
    val textCodeTitle: StringResource
    val textCodeDescMarkdown: StringResource
    val textCodeBtn: StringResource
    val textCodeError: StringResource

    val verificationEmailType: BSBEmailVerificationType

    @Stable
    data class ForgotPassword(
        override val email: String
    ) : InternalAuthOtpType {
        override val textTitle = Res.string.login_otp_screen_forgot_title
        override val textDesc = Res.string.login_otp_screen_forgot_desc
        override val textCodeTitle = Res.string.login_otp_screen_forgot_code_title
        override val textCodeDescMarkdown = Res.string.login_otp_screen_forgot_code_desc_markdown
        override val textCodeBtn = Res.string.login_otp_screen_forgot_code_btn
        override val textCodeError = Res.string.login_otp_screen_forgot_code_error
        override val verificationEmailType = BSBEmailVerificationType.RESET_PASSWORD
    }

    @Stable
    data class VerifyEmail(
        override val email: String
    ) : InternalAuthOtpType {
        override val textTitle = Res.string.login_otp_screen_verify_title
        override val textDesc = Res.string.login_otp_screen_verify_desc
        override val textCodeTitle = Res.string.login_otp_screen_verify_code_title
        override val textCodeDescMarkdown = Res.string.login_otp_screen_verify_code_desc_markdown
        override val textCodeBtn = Res.string.login_otp_screen_verify_code_btn
        override val textCodeError = Res.string.login_otp_screen_verify_code_error
        override val verificationEmailType = BSBEmailVerificationType.SIGN_UP
    }
}

fun AuthOtpType.toInternalAuthOtpType(): InternalAuthOtpType {
    return when (this) {
        is AuthOtpType.ForgotPassword -> InternalAuthOtpType.ForgotPassword(email)
        is AuthOtpType.VerifyEmail -> InternalAuthOtpType.VerifyEmail(email)
    }
}
