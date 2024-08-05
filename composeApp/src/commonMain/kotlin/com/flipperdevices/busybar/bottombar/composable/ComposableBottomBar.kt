package com.flipperdevices.busybar.bottombar.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.flipperdevices.busybar.core.theme.LocalPallet
import com.flipperdevices.busybar.bottombar.config.BottomBarEnum
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ComposableBottomBar(
    selectedTab: BottomBarEnum,
    onSelect: (BottomBarEnum) -> Unit
) = TabRow(
    modifier = Modifier.fillMaxWidth(),
    selectedTabIndex = BottomBarEnum.entries.indexOf(selectedTab),
    backgroundColor = LocalPallet.current.background.tertiary,
    contentColor = Color.White
) {
    BottomBarEnum.entries.forEach { bottomBarTab ->
        Tab(
            onClick = { onSelect(bottomBarTab) },
            selected = selectedTab == bottomBarTab,
            text = { Text(stringResource(bottomBarTab.tabName)) },
            icon = {
                Icon(
                    painter = painterResource(bottomBarTab.icon),
                    contentDescription = null
                )
            }
        )
    }
}