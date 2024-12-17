package com.flipperdevices.bsb.auth.confirmpassword.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import busystatusbar.components.bsb.auth.confirmpassword.impl.generated.resources.Res
import busystatusbar.components.bsb.auth.confirmpassword.impl.generated.resources.login_confirmpassword_btn
import busystatusbar.components.bsb.auth.confirmpassword.impl.generated.resources.login_confirmpassword_password_placeholder
import busystatusbar.components.bsb.auth.confirmpassword.impl.generated.resources.pic_confirm_password
import com.flipperdevices.bsb.auth.common.composable.BusyBarButtonComposable
import com.flipperdevices.bsb.auth.common.composable.subaction.AuthTextSubActionComposable
import com.flipperdevices.bsb.auth.confirmpassword.model.ConfirmPasswordScreenState
import com.flipperdevices.bsb.auth.confirmpassword.model.InternalConfirmPasswordType
import com.flipperdevices.bsb.auth.confirmpassword.model.PasswordFieldsState
import com.flipperdevices.bsb.core.theme.LocalBusyBarFonts
import com.flipperdevices.bsb.core.theme.LocalPallet
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Suppress("LongMethod")
fun ConfirmPasswordScreenComposable(
    confirmPasswordType: InternalConfirmPasswordType,
    screenState: ConfirmPasswordScreenState,
    fieldsState: PasswordFieldsState,
    onBack: () -> Unit,
    onConfirm: () -> Unit,
    onPasswordFieldChange: (String) -> Unit,
    onConfirmFieldChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier
            .imePadding()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.padding(top = 32.dp),
            painter = painterResource(Res.drawable.pic_confirm_password),
            contentDescription = null
        )

        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(confirmPasswordType.textDesc),
            textAlign = TextAlign.Center,
            fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
            fontWeight = FontWeight.W400,
            color = LocalPallet.current.black.invert
        )

        Text(
            text = confirmPasswordType.original.email,
            textAlign = TextAlign.Center,
            fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
            fontWeight = FontWeight.W600,
            color = LocalPallet.current.black.invert
        )

        PasswordWithErrorTextFieldComposable(
            modifier = Modifier.padding(top = 16.dp),
            hint = Res.string.login_confirmpassword_password_placeholder,
            fieldState = fieldsState.passwordField,
            inProgress = screenState.inProgress,
            onPasswordChange = onPasswordFieldChange,
            onFocus = { coroutineScope.launch { bringIntoViewRequester.bringIntoView() } },
            fieldModifier = Modifier.focusRequester(focusRequester),
        )
        LaunchedEffect(focusRequester) {
            focusRequester.requestFocus()
        }
        PasswordWithErrorTextFieldComposable(
            modifier = Modifier.padding(top = 16.dp),
            hint = Res.string.login_confirmpassword_password_placeholder,
            fieldState = fieldsState.confirmField,
            inProgress = screenState.inProgress,
            onPasswordChange = onConfirmFieldChange,
            onFocus = { coroutineScope.launch { bringIntoViewRequester.bringIntoView() } },
            fieldModifier = Modifier
        )

        BusyBarButtonComposable(
            modifier = Modifier
                .bringIntoViewRequester(bringIntoViewRequester)
                .padding(vertical = 32.dp)
                .fillMaxWidth(),
            text = Res.string.login_confirmpassword_btn,
            onClick = onConfirm,
            inProgress = screenState.inProgress,
            disabled = fieldsState.confirmDisabled || screenState.inProgress
        )

        confirmPasswordType.textBack?.let { textBack ->
            AuthTextSubActionComposable(
                modifier = Modifier,
                onClick = onBack,
                text = textBack
            )
        }
    }
}
