package com.flipperdevices.bsb.timer.setup.composable

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun NumberSelectorComposable(
    modifier: Modifier,
    count: Int,
    onSelect: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { count })

    VerticalPager(state = pagerState) { page ->
        Text(
            text = page.toString(),
            fontSize = 100.sp
        )
    }
}