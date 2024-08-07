package com.flipperdevices.busybar.apps.api

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.busybar.apps.composable.AppsScreenComposable
import com.flipperdevices.busybar.core.decompose.DecomposeComponent
import com.flipperdevices.busybar.core.decompose.DecomposeOnBackParameter
import com.flipperdevices.busybar.root.api.RootNavigationApi
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class AppsDecomposeComponent(
    @Assisted componentContext: ComponentContext,
    @Assisted private val navigationApi: RootNavigationApi,
    @Assisted private val onBack: DecomposeOnBackParameter
) : DecomposeComponent, ComponentContext by componentContext {
    @Composable
    override fun Render(modifier: Modifier) {
        AppsScreenComposable(
            modifier.statusBarsPadding(),
            onBack = onBack::invoke,
            onAppClick = {
                navigationApi.onAppSelected(it)
                onBack()
            }
        )
    }
}