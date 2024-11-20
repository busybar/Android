package com.flipperdevices.bsb.auth.main.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busystatusbar.components.bsb.auth.main.impl.generated.resources.Res
import busystatusbar.components.bsb.auth.main.impl.generated.resources.ic_apple
import busystatusbar.components.bsb.auth.main.impl.generated.resources.ic_apple_dark
import busystatusbar.components.bsb.auth.main.impl.generated.resources.ic_google
import busystatusbar.components.bsb.auth.main.impl.generated.resources.ic_microsoft
import busystatusbar.components.bsb.auth.main.impl.generated.resources.login_main_btn
import busystatusbar.components.bsb.auth.main.impl.generated.resources.login_main_email_title
import busystatusbar.components.bsb.auth.main.impl.generated.resources.pic_busycloud
import busystatusbar.components.bsb.auth.main.impl.generated.resources.pic_busycloud_dark
import com.flipperdevices.bsb.auth.common.composable.BusyBarButtonComposable
import com.flipperdevices.bsb.auth.common.composable.UiConstants
import com.flipperdevices.bsb.core.theme.LocalBusyBarFonts
import com.flipperdevices.bsb.core.theme.LocalPallet
import com.flipperdevices.bsb.auth.main.model.AuthMainState
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AuthMainComposable(
    state: AuthMainState,
    onLogin: (String) -> Unit,
    modifier: Modifier
) {
    val verticalScroll = rememberScrollState()
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier
            .verticalScroll(verticalScroll)
            .imePadding()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(
                if (MaterialTheme.colors.isLight) {
                    Res.drawable.pic_busycloud
                } else {
                    Res.drawable.pic_busycloud_dark
                }
            ),
            contentDescription = null
        )

        Text(
            modifier = Modifier.padding(vertical = 16.dp),
            text = stringResource(Res.string.login_main_email_title),
            fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
            fontSize = 16.sp,
            color = LocalPallet.current.black.invert,
            fontWeight = FontWeight.W400
        )

        var email by remember { mutableStateOf("") }

        EmailEditFieldComposable(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    if (it.isFocused) {
                        coroutineScope.launch { bringIntoViewRequester.bringIntoView() }
                    }
                },
            text = email,
            onTextChange = { email = it },
            disabled = when (state) {
                AuthMainState.WaitingForInput -> false
                AuthMainState.AuthInProgress -> true
            }
        )

        BusyBarButtonComposable(
            modifier = Modifier
                .padding(vertical = 32.dp)
                .fillMaxWidth()
                .bringIntoViewRequester(bringIntoViewRequester),
            text = Res.string.login_main_btn,
            onClick = { onLogin(email) },
            inProgress = when (state) {
                AuthMainState.WaitingForInput -> false
                AuthMainState.AuthInProgress -> true
            }
        )

        OrLineComposable(
            Modifier.fillMaxWidth()
                .padding(bottom = 32.dp)
        )

        Row(
            Modifier.fillMaxWidth().graphicsLayer {
                when (state) {
                    AuthMainState.WaitingForInput -> {}
                    AuthMainState.AuthInProgress -> {
                        this.alpha = UiConstants.ALPHA_DISABLED
                    }
                }
            },
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SignInWithButtonComposable(
                modifier = Modifier.weight(1f),
                icon = Res.drawable.ic_google,
                onClick = {}
            )
            SignInWithButtonComposable(
                modifier = Modifier.weight(1f),
                icon = if (MaterialTheme.colors.isLight) {
                    Res.drawable.ic_apple
                } else {
                    Res.drawable.ic_apple_dark
                },
                onClick = {}
            )
            SignInWithButtonComposable(
                modifier = Modifier.weight(1f),
                icon = Res.drawable.ic_microsoft,
                onClick = {}
            )
        }
    }
}