package com.flipperdevices.busybar.bottombar.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.router.stack.ChildStack
import com.lionzxy.flippertesttask.bottombar.config.BottomBarConfig
import com.flipperdevices.busybar.bottombar.config.BottomBarEnum
import com.flipperdevices.busybar.core.decompose.DecomposeComponent

@Composable
fun ComposableBottomBarScreen(
    childStack: ChildStack<BottomBarConfig, DecomposeComponent>,
    onSelect: (BottomBarEnum) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            ComposableBottomBar(
                selectedTab = childStack.active.configuration.enum,
                onSelect = onSelect
            )
        }
    ) { paddingValues ->
        Box(Modifier.padding(paddingValues)) {
            Children(
                stack = childStack,
            ) {
                it.instance.Render(Modifier)
            }
        }
    }
}