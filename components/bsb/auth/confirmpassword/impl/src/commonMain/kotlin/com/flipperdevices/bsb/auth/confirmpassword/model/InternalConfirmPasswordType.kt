package com.flipperdevices.bsb.auth.confirmpassword.model

import busystatusbar.components.bsb.auth.confirmpassword.impl.generated.resources.Res
import busystatusbar.components.bsb.auth.confirmpassword.impl.generated.resources.login_confirmpassword_forget_desc
import busystatusbar.components.bsb.auth.confirmpassword.impl.generated.resources.login_confirmpassword_forget_title
import busystatusbar.components.bsb.auth.confirmpassword.impl.generated.resources.login_confirmpassword_register_back
import busystatusbar.components.bsb.auth.confirmpassword.impl.generated.resources.login_confirmpassword_register_desc
import busystatusbar.components.bsb.auth.confirmpassword.impl.generated.resources.login_confirmpassword_register_title
import org.jetbrains.compose.resources.StringResource

interface InternalConfirmPasswordType {
    val email: String
    val textTitle: StringResource
    val textDesc: StringResource
    val textBack: StringResource?

    data class ResetPassword(
        override val email: String
    ) : InternalConfirmPasswordType {
        override val textTitle = Res.string.login_confirmpassword_forget_title
        override val textDesc = Res.string.login_confirmpassword_forget_desc
        override val textBack = null
    }

    data class SignUpPassword(
        override val email: String
    ) : InternalConfirmPasswordType {
        override val textTitle = Res.string.login_confirmpassword_register_title
        override val textDesc = Res.string.login_confirmpassword_register_desc
        override val textBack = Res.string.login_confirmpassword_register_back
    }
}

fun ConfirmPasswordType.toInternalPasswordType() = when (this) {
    is ConfirmPasswordType.ResetPassword -> InternalConfirmPasswordType.ResetPassword(email)
    is ConfirmPasswordType.SignUpPassword -> InternalConfirmPasswordType.SignUpPassword(email)
}