package com.flipperdevices.busybar.apps.composable.apps

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.pic_apps_frame
import busystatusbar.composeapp.generated.resources.pic_device_main
import com.flipperdevices.busybar.core.theme.BusyBarThemeInternal
import org.jetbrains.compose.resources.imageResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.roundToInt

@Composable
fun BusyBarAppPreviewComposable(
    busyBarApp: BusyBarApp,
    modifier: Modifier = Modifier
) = ComposableBusyBarMockupInternalRaw(modifier) {
    val imageResource = imageResource(busyBarApp.pic)

    val imageDrawPainter = remember(imageResource) {
        return@remember BitmapPainter(
            imageResource,
            filterQuality = FilterQuality.None
        )
    }

    Image(
        modifier = Modifier.fillMaxSize(),
        contentDescription = null,
        painter = imageDrawPainter,
        contentScale = ContentScale.FillWidth
    )
}


internal const val BUSYBAR_DEFAULT_HEIGHT = 112f
internal const val BUSYBAR_DEFAULT_WIDTH = 365f
internal const val BUSYBAR_RATIO = BUSYBAR_DEFAULT_WIDTH / BUSYBAR_DEFAULT_HEIGHT

private const val IMAGE_WIDTH_PADDING_PERCENT = 12f / BUSYBAR_DEFAULT_WIDTH
private const val IMAGE_HEIGHT_PADDING_PERCENT = 22f / BUSYBAR_DEFAULT_HEIGHT
private const val IMAGE_WIDTH_PERCENT = 341f / BUSYBAR_DEFAULT_WIDTH
private const val IMAGE_HEIGHT_PERCENT = 78f / BUSYBAR_DEFAULT_HEIGHT


@Composable
fun ComposableBusyBarMockupInternalRaw(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    BoxWithConstraints(
        modifier
            .aspectRatio(
                ratio = BUSYBAR_RATIO
            )
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(Res.drawable.pic_apps_frame),
            contentDescription = null
        )
        Box(
            modifier = Modifier
                .padding(
                    start = remember(maxWidth) { maxWidth * IMAGE_WIDTH_PADDING_PERCENT },
                    top = remember(maxHeight) { maxHeight * IMAGE_HEIGHT_PADDING_PERCENT }
                )
                .size(
                    width = remember(maxWidth) { maxWidth * IMAGE_WIDTH_PERCENT },
                    height = remember(maxHeight) { maxHeight * IMAGE_HEIGHT_PERCENT }
                ),
        ) {
            content()
        }
    }
}

@Preview
@Composable
private fun BusyBarAppStreamingComposablePreview() {
    BusyBarThemeInternal {
        BusyBarAppPreviewComposable(
            BusyBarApp.default
        )
    }
}