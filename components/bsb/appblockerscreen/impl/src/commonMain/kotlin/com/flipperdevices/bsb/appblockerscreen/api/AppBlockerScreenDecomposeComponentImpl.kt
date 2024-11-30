package com.flipperdevices.bsb.appblockerscreen.api

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import busystatusbar.components.bsb.appblockerscreen.impl.generated.resources.Res
import busystatusbar.components.bsb.appblockerscreen.impl.generated.resources.appblocker_screen_desc
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.bsb.core.theme.LocalBusyBarFonts
import com.flipperdevices.bsb.core.theme.LocalPallet
import com.flipperdevices.bsb.preference.api.ThemeStatusBarIconStyleProvider
import com.flipperdevices.core.di.AppGraph
import com.flipperdevices.ui.decompose.statusbar.StatusBarIconStyleProvider
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import org.jetbrains.compose.resources.stringResource
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@Inject
class AppBlockerScreenDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
    @Assisted private val packageName: String,
    statusBarIconStyleProvider: ThemeStatusBarIconStyleProvider
) : AppBlockerScreenDecomposeComponent(componentContext),
    StatusBarIconStyleProvider by statusBarIconStyleProvider {
    @Composable
    override fun Render(modifier: Modifier) {
        Box(
            modifier
                .fillMaxSize()
                .safeDrawingPadding(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(Res.string.appblocker_screen_desc, packageName),
                color = LocalPallet.current.black.invert,
                fontSize = 18.sp,
                fontFamily = LocalBusyBarFonts.current.pragmatica,
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center
            )
        }
    }

    @Inject
    @ContributesBinding(AppGraph::class, AppBlockerScreenDecomposeComponent.Factory::class)
    class Factory(
        private val factory: (
            componentContext: ComponentContext,
            packageName: String
        ) -> AppBlockerScreenDecomposeComponentImpl
    ) : AppBlockerScreenDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            packageName: String
        ) = factory(componentContext, packageName)
    }
}