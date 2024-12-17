package com.flipperdevices.bsb.auth.signup.api

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busystatusbar.components.bsb.auth.signup.impl.generated.resources.Res
import busystatusbar.components.bsb.auth.signup.impl.generated.resources.login_signup_btn
import busystatusbar.components.bsb.auth.signup.impl.generated.resources.login_signup_desc
import busystatusbar.components.bsb.auth.signup.impl.generated.resources.login_signup_title
import busystatusbar.components.bsb.auth.signup.impl.generated.resources.pic_signup
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.bsb.auth.common.composable.BusyBarButtonComposable
import com.flipperdevices.bsb.auth.common.composable.appbar.LogInAppBarComposable
import com.flipperdevices.bsb.core.theme.LocalBusyBarFonts
import com.flipperdevices.bsb.core.theme.LocalPallet
import com.flipperdevices.ui.decompose.DecomposeOnBackParameter
import com.flipperdevices.ui.decompose.ScreenDecomposeComponent
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Inject
class SignupScreenDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
    @Assisted private val onBack: DecomposeOnBackParameter,
    @Assisted private val email: String,
    @Assisted private val onContinue: () -> Unit
) : ScreenDecomposeComponent(componentContext) {
    @Composable
    override fun Render(modifier: Modifier) {
        Column(
            Modifier.fillMaxSize().systemBarsPadding()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogInAppBarComposable(
                Res.string.login_signup_title,
                onBack = onBack::invoke
            )

            Image(
                painter = painterResource(Res.drawable.pic_signup),
                contentDescription = null,
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(Res.string.login_signup_desc),
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

            BusyBarButtonComposable(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 116.dp,
                        bottom = 32.dp
                    ),
                text = Res.string.login_signup_btn,
                onClick = onContinue
            )
        }
    }
}
