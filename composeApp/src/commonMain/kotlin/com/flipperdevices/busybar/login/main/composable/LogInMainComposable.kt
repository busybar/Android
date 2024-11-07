package com.flipperdevices.busybar.login.main.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.login_main_title
import com.flipperdevices.busybar.login.common.composable.LogInAppBarComposable

@Composable
fun LogInMainComposable(modifier: Modifier) {
    ConstraintLayout(
        modifier
            .padding(horizontal = 16.dp)
    ) {
        val (editText) = createRefs()

        EmailEditFieldComposable(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(editText) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}