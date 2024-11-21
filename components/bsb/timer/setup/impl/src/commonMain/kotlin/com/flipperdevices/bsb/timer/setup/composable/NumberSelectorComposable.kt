package com.flipperdevices.bsb.timer.setup.composable

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.lerp
import com.flipperdevices.bsb.core.theme.LocalPallet
import com.flipperdevices.bsb.timer.setup.utils.NoLimitPagerSnapDistance
import kotlin.math.absoluteValue

@Composable
fun NumberSelectorComposable(
    modifier: Modifier,
    count: Int,
    initialNumber: Int,
    onSelect: () -> Unit
) {
    val pagerSize = Int.MAX_VALUE - 1
    val initialPage = remember(
        pagerSize,
        initialNumber,
        count
    ) {
        (pagerSize / 2).floorDiv(count) * count + initialNumber
    }
    val pagerState = rememberPagerState(
        initialPage = initialPage,
        pageCount = {
            pagerSize
        })
    val fling = PagerDefaults.flingBehavior(
        state = pagerState,
        pagerSnapDistance = NoLimitPagerSnapDistance
    )
    var contentHeightDp by remember { mutableStateOf<Dp?>(null) }
    val backgroundColor = MaterialTheme.colors.background
    BoxWithConstraints(modifier = modifier) {
        val contentPadding = contentHeightDp?.let {
            (maxHeight - it) / 2
        } ?: 0.dp
        VerticalPager(
            modifier = Modifier.wrapContentHeight()
                .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
                .drawWithContent {
                    drawContent()
                    val brush = Brush.verticalGradient(
                        0f to backgroundColor.copy(alpha = 0f),
                        0.35f to backgroundColor.copy(alpha = 1f),
                        0.7f to backgroundColor.copy(alpha = 1f),
                        1f to backgroundColor.copy(alpha = 0f)
                    )
                    drawRect(brush = brush, blendMode = BlendMode.DstIn)
                },
            state = pagerState,
            contentPadding = PaddingValues(vertical = contentPadding),
            flingBehavior = fling
        ) { page ->
            val activeColor = LocalPallet.current.black.invert
            val inactiveColor = LocalPallet.current.transparent.blackInvert.tertiary
            val textColor = remember(
                activeColor,
                inactiveColor,
                pagerState.currentPage,
                page,
                pagerState.currentPageOffsetFraction
            ) {
                val pageOffset = ((pagerState.currentPage - page) + pagerState
                    .currentPageOffsetFraction).absoluteValue
                lerp(
                    start = activeColor,
                    stop = inactiveColor,
                    fraction = pageOffset.coerceIn(0f, 1f)
                )
            }

            val localDensity = LocalDensity.current
            NumberElementComposable(
                modifier = Modifier.onGloballyPositioned { coordinates ->
                    contentHeightDp = with(localDensity) { coordinates.size.height.toDp() }
                },
                number = page.mod(count),
                color = textColor
            )
        }
    }
}

@Composable
private fun NumberElementComposable(
    modifier: Modifier,
    number: Int,
    color: Color
) {
    val numberText = if (number < 10) {
        "0$number"
    } else number.toString()
    Text(
        modifier = modifier,
        text = numberText,
        fontSize = 100.sp,
        color = color
    )
}