package com.flipperdevices.bsb.timer.main.composable

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flipperdevices.bsb.core.theme.LocalBusyBarFonts
import com.flipperdevices.bsb.core.theme.LocalPallet
import com.flipperdevices.bsb.timer.background.model.TimerState

@Composable
fun TimerTimeComposable(
    modifier: Modifier,
    timerState: TimerState
) {
    Row(modifier = modifier) {
        TimerNumberComposable(timerState.minute)
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = ":",
            fontSize = 100.sp,
            color = LocalPallet.current.black.invert,
            fontWeight = FontWeight.W500,
            fontFamily = LocalBusyBarFonts.current.pragmatica,
            textAlign = TextAlign.Center
        )
        TimerNumberComposable(timerState.second)
    }
}

@Composable
private fun TimerNumberComposable(number: Int) {
    val numberText = if (number < 10) {
        "0$number"
    } else number.toString()
    Row {
        numberText.forEach { symbol ->
            AnimatedContent(
                targetState = symbol,
                transitionSpec = {
                    slideInVertically { height -> height } + fadeIn() togetherWith
                            slideOutVertically { height -> -height } + fadeOut()
                }
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 2.dp)
                        .width(64.dp)
                        .wrapContentHeight(
                            align = Alignment.CenterVertically, // aligns to the center vertically (default value)
                            unbounded = true // Makes sense if the container size less than text's height
                        ),
                    text = symbol.toString(),
                    fontSize = 100.sp,
                    color = LocalPallet.current.black.invert,
                    fontWeight = FontWeight.W500,
                    fontFamily = LocalBusyBarFonts.current.pragmatica,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}