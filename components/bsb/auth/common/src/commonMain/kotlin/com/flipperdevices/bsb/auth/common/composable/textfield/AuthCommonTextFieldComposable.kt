package com.flipperdevices.bsb.auth.common.composable.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flipperdevices.bsb.auth.common.composable.UiConstants
import com.flipperdevices.bsb.core.theme.LocalBusyBarFonts
import com.flipperdevices.bsb.core.theme.LocalPallet
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AuthCommonTextFieldComposable(
    modifier: Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    hint: StringResource,
    icon: DrawableResource,
    endBlock: (@Composable () -> Unit)? = null,
    keyboardOptions: KeyboardOptions,
    disabled: Boolean = false,
    maxLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    BasicTextField(
        modifier = modifier.graphicsLayer {
            if (disabled) {
                this.alpha = UiConstants.ALPHA_DISABLED
            }
        },
        enabled = disabled.not(),
        value = text,
        keyboardOptions = keyboardOptions,
        onValueChange = { onTextChange(it) },
        maxLines = maxLines,
        visualTransformation = visualTransformation,
        textStyle = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.W500,
            fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
            color = LocalPallet.current.black.invert
        ),
        cursorBrush = SolidColor(LocalPallet.current.black.invert),
        decorationBox = { innerTextField ->
            AuthEditDecorationBox(
                innerTextField = innerTextField,
                icon = icon,
                endBlock = endBlock,
                hintVisibility = text.isEmpty(),
                hint = hint
            )
        }
    )
}

@Composable
fun AuthEditDecorationBox(
    innerTextField: @Composable () -> Unit,
    icon: DrawableResource,
    hint: StringResource,
    endBlock: (@Composable () -> Unit)? = null,
    hintVisibility: Boolean
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, LocalPallet.current.neutral.quinary, RoundedCornerShape(8.dp))
            .background(LocalPallet.current.neutral.septenary)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(LocalPallet.current.transparent.blackInvert.quaternary)
                .padding(4.dp)
                .size(16.dp),
            painter = painterResource(icon),
            contentDescription = null,
            tint = LocalPallet.current.neutral.tertiary
        )
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.CenterStart
        ) {
            if (hintVisibility) {
                Text(
                    text = stringResource(hint),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
                    color = LocalPallet.current.neutral.tertiary
                )
            }
            innerTextField()
        }

        endBlock?.invoke()
    }
}