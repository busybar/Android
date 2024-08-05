package com.flipperdevices.busybar.bottombar.composable

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.flipperdevices.busybar.core.theme.LocalPallet
import com.flipperdevices.busybar.bottombar.config.BottomBarEnum
import com.flipperdevices.busybar.core.theme.LocalBusyBarFonts
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
    indicator = {}
) {
    BottomBarEnum.entries.forEach { bottomBarTab ->
        val isSelected = selectedTab == bottomBarTab
        Tab(
            onClick = { onSelect(bottomBarTab) },
            selected = isSelected,
            text = {
                Text(
                    text = stringResource(bottomBarTab.tabName),
                    fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
                    fontWeight = FontWeight.W500,
                    fontSize = 12.sp
                )
            },
            selectedContentColor = LocalPallet.current.invert.black,
            unselectedContentColor = LocalPallet.current.neutral.tertiary,
            icon = {
                Icon(
                    painter = painterResource(bottomBarTab.icon),
                    contentDescription = null
                )
            }
        )
    }
}