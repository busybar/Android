package com.flipperdevices.busybar.login.main.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.ic_apple
import busystatusbar.composeapp.generated.resources.ic_google
import busystatusbar.composeapp.generated.resources.ic_microsoft
import busystatusbar.composeapp.generated.resources.login_main_btn
import busystatusbar.composeapp.generated.resources.login_main_email_title
import busystatusbar.composeapp.generated.resources.login_main_title
import busystatusbar.composeapp.generated.resources.pic_busycloud
import com.flipperdevices.busybar.login.common.composable.BusyBarButtonComposable
import com.flipperdevices.busybar.login.common.composable.LogInAppBarComposable
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LogInMainComposable(
    modifier: Modifier
) {
    val verticalScroll = rememberScrollState()
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()
    ConstraintLayout(
        modifier
            .verticalScroll(verticalScroll)
            .imePadding()
            .padding(horizontal = 16.dp)
    ) {
        val (signIn, orLine, btn, editText, title, image) = createRefs()

        Image(
            modifier = Modifier.constrainAs(image) {
                bottom.linkTo(title.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            painter = painterResource(Res.drawable.pic_busycloud),
            contentDescription = null
        )

        Text(
            modifier = Modifier.padding(vertical = 16.dp)
                .constrainAs(title) {
                    bottom.linkTo(editText.top)
                },
            text = stringResource(Res.string.login_main_email_title)
        )

        EmailEditFieldComposable(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(editText) {
                    bottom.linkTo(btn.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }.onFocusChanged {
                    if (it.isFocused) {
                        coroutineScope.launch { bringIntoViewRequester.bringIntoView() }
                    }
                },
        )

        BusyBarButtonComposable(
            modifier = Modifier
                .padding(vertical = 32.dp)
                .fillMaxWidth()
                .bringIntoViewRequester(bringIntoViewRequester)
                .constrainAs(btn) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            text = Res.string.login_main_btn,
            onClick = {}
        )

        OrLineComposable(
            Modifier.fillMaxWidth()
                .padding(bottom = 32.dp)
                .constrainAs(orLine) {
                    top.linkTo(btn.bottom)
                }
        )

        Row(
            Modifier.fillMaxWidth()
                .constrainAs(signIn) {
                    top.linkTo(orLine.bottom)
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
                icon = Res.drawable.ic_apple,
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