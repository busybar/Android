package com.flipperdevices.bsb.auth.confirmpassword.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.flipperdevices.bsb.auth.common.composable.textfield.PasswordTextFieldComposable
import com.flipperdevices.bsb.auth.confirmpassword.model.PasswordFieldState
import com.flipperdevices.bsb.core.theme.LocalBusyBarFonts
import com.flipperdevices.bsb.core.theme.LocalPallet
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun PasswordWithErrorTextFieldComposable(
    onFocus: () -> Unit,
    hint: StringResource,
    fieldState: PasswordFieldState,
    inProgress: Boolean,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    fieldModifier: Modifier = Modifier,
) {
    Column(modifier) {
        PasswordTextFieldComposable(
            modifier = fieldModifier.onFocusChanged {
                if (it.isFocused) {
                    onFocus()
                }
            },
            password = fieldState.text,
            onPasswordChange = onPasswordChange,
            disabled = inProgress,
            hint = hint,
            isError = fieldState.validationError,
            onAutofill = {
                onPasswordChange(it)
                onFocus()
            }
        )
        fieldState.subText?.let { subText ->
            Text(
                text = stringResource(subText),
                fontSize = 16.sp,
                fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
                fontWeight = FontWeight.W500,
                color = if (fieldState.validationError) {
                    LocalPallet.current.danger.primary
                } else {
                    LocalPallet.current.neutral.tertiary
                }
            )
        }
    }
}
