package com.flipperdevices.busybar.auth.login.composable

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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.login_signin_btn
import busystatusbar.composeapp.generated.resources.login_signin_desc
import busystatusbar.composeapp.generated.resources.login_signin_forgot_password
import busystatusbar.composeapp.generated.resources.pic_user_password
import com.flipperdevices.busybar.auth.common.composable.BusyBarButtonComposable
import com.flipperdevices.busybar.core.theme.LocalBusyBarFonts
import com.flipperdevices.busybar.core.theme.LocalPallet
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginPasswordComposable(
    modifier: Modifier,
    email: String
) {
    val verticalScroll = rememberScrollState()
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier.verticalScroll(verticalScroll)
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(Res.drawable.pic_user_password),
            contentDescription = null,
        )
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(Res.string.login_signin_desc),
            fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
            fontSize = 16.sp,
            fontWeight = FontWeight.W400,
            textAlign = TextAlign.Center,
            color = LocalPallet.current.black.invert
        )
        Text(
            text = email,
            fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
            fontSize = 16.sp,
            fontWeight = FontWeight.W600,
            textAlign = TextAlign.Center,
            color = LocalPallet.current.black.invert
        )
        PasswordTextFieldComposable(
            Modifier.fillMaxWidth()
                .padding(top = 16.dp)
                .onFocusChanged {
                    if (it.isFocused) {
                        coroutineScope.launch { bringIntoViewRequester.bringIntoView() }
                    }
                }
        )
        BusyBarButtonComposable(
            modifier = Modifier
                .fillMaxWidth()
                .bringIntoViewRequester(bringIntoViewRequester)
                .padding(
                    top = 52.dp,
                    bottom = 32.dp
                ),
            text = Res.string.login_signin_btn,
            onClick = {}
        )
        Text(
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = 12.dp),
            text = stringResource(Res.string.login_signin_forgot_password),
            textAlign = TextAlign.End,
            fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
            fontSize = 16.sp,
            fontWeight = FontWeight.W500,
            color = LocalPallet.current.brand.primary
        )
    }
}