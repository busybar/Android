package com.flipperdevices.busybar.auth.common.composable.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.login_main_email_hint
import com.flipperdevices.busybar.core.theme.LocalBusyBarFonts
import com.flipperdevices.busybar.core.theme.LocalPallet
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AuthCommonTextFieldComposable(
    modifier: Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    icon: DrawableResource,
    endBlock: (@Composable () -> Unit)? = null,
    keyboardOptions: KeyboardOptions
) {
    BasicTextField(
        modifier = modifier,
        value = text,
        keyboardOptions = keyboardOptions,
        onValueChange = { onTextChange(it) },
        decorationBox = { innerTextField ->
            EmailEditDecorationBox(
                innerTextField = innerTextField,
                icon = icon,
                endBlock = endBlock,
                hintVisibility = text.isEmpty(),
            )
        }
    )
}

@Composable
fun EmailEditDecorationBox(
    innerTextField: @Composable () -> Unit,
    icon: DrawableResource,
    endBlock: (@Composable () -> Unit)? = null,
    hintVisibility: Boolean
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, LocalPallet.current.neutral.quinary, RoundedCornerShape(8.dp))
            .background(LocalPallet.current.neutral.septenary)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(LocalPallet.current.transparent.blackInvert.quaternary)
                .padding(4.dp)
                .size(16.dp),
            painter = painterResource(icon),
            contentDescription = null
        )
        Box(
            modifier = Modifier.fillMaxWidth()
                .padding(start = 12.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            if (hintVisibility) {
                Text(
                    text = stringResource(Res.string.login_main_email_hint),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
                    color = LocalPallet.current.neutral.tertiary
                )
            }
            innerTextField()
        }

        endBlock?.invoke()
    }
}