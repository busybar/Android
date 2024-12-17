package com.flipperdevices.bsb.auth.confirmpassword.model

import busystatusbar.components.bsb.auth.confirmpassword.impl.generated.resources.Res
import busystatusbar.components.bsb.auth.confirmpassword.impl.generated.resources.login_confirmpassword_forget_desc
import busystatusbar.components.bsb.auth.confirmpassword.impl.generated.resources.login_confirmpassword_forget_error_desc
import busystatusbar.components.bsb.auth.confirmpassword.impl.generated.resources.login_confirmpassword_forget_error_title
import busystatusbar.components.bsb.auth.confirmpassword.impl.generated.resources.login_confirmpassword_forget_title
import busystatusbar.components.bsb.auth.confirmpassword.impl.generated.resources.login_confirmpassword_register_back
import busystatusbar.components.bsb.auth.confirmpassword.impl.generated.resources.login_confirmpassword_register_desc
import busystatusbar.components.bsb.auth.confirmpassword.impl.generated.resources.login_confirmpassword_register_error_desc
import busystatusbar.components.bsb.auth.confirmpassword.impl.generated.resources.login_confirmpassword_register_error_title
import busystatusbar.components.bsb.auth.confirmpassword.impl.generated.resources.login_confirmpassword_register_title
import org.jetbrains.compose.resources.StringResource

sealed interface InternalConfirmPasswordType {
    val original: ConfirmPasswordType

    val textTitle: StringResource
    val textDesc: StringResource
    val textBack: StringResource?
    val errorPopUpTitle: StringResource
    val errorPopUpDesc: StringResource

    data class ResetPassword(
        override val original: ConfirmPasswordType
    ) : InternalConfirmPasswordType {
        override val textTitle = Res.string.login_confirmpassword_forget_title
        override val textDesc = Res.string.login_confirmpassword_forget_desc
        override val textBack = null
        override val errorPopUpTitle = Res.string.login_confirmpassword_register_error_title
        override val errorPopUpDesc = Res.string.login_confirmpassword_register_error_desc
    }

    data class SignUpPassword(
        override val original: ConfirmPasswordType
    ) : InternalConfirmPasswordType {
        override val textTitle = Res.string.login_confirmpassword_register_title
        override val textDesc = Res.string.login_confirmpassword_register_desc
        override val textBack = Res.string.login_confirmpassword_register_back
        override val errorPopUpTitle = Res.string.login_confirmpassword_forget_error_title
        override val errorPopUpDesc = Res.string.login_confirmpassword_forget_error_desc
    }
}

fun ConfirmPasswordType.toInternalPasswordType() = when (this) {
    is ConfirmPasswordType.ResetPassword -> InternalConfirmPasswordType.ResetPassword(this)
    is ConfirmPasswordType.SignUpPassword -> InternalConfirmPasswordType.SignUpPassword(this)
}
