package com.flipperdevices.busybar.login.main.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.ic_user
import busystatusbar.composeapp.generated.resources.login_main_email_hint
import com.flipperdevices.busybar.core.theme.LocalBusyBarFonts
import com.flipperdevices.busybar.core.theme.LocalPallet
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun EmailEditFieldComposable(
    modifier: Modifier,
) {
    var text by remember { mutableStateOf("") }

    BasicTextField(
        modifier = modifier,
        value = text,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        onValueChange = { text = it },
        decorationBox = { innerTextField ->
            EmailEditDecorationBox(innerTextField, hintVisibility = text.isEmpty())
        }
    )
}

@Composable
fun EmailEditDecorationBox(
    innerTextField: @Composable () -> Unit,
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
            painter = painterResource(Res.drawable.ic_user),
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
    }
}