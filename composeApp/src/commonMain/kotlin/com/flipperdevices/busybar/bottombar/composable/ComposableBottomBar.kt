package com.flipperdevices.busybar.bottombar.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
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
    modifier = Modifier.fillMaxWidth()
        .background(LocalPallet.current.background.tertiary)
        .navigationBarsPadding(),
    selectedTabIndex = BottomBarEnum.entries.indexOf(selectedTab),
    backgroundColor = LocalPallet.current.background.tertiary,
    indicator = {},
    divider = {}
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
                    fontWeight = FontWeight.Normal,
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