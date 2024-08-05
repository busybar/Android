package com.flipperdevices.busybar.core.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.ppneuemontreal_bold
import busystatusbar.composeapp.generated.resources.ppneuemontreal_bold_italic
import busystatusbar.composeapp.generated.resources.ppneuemontreal_italic
import busystatusbar.composeapp.generated.resources.ppneuemontreal_light
import busystatusbar.composeapp.generated.resources.ppneuemontreal_light_italic
import busystatusbar.composeapp.generated.resources.ppneuemontreal_medium
import busystatusbar.composeapp.generated.resources.ppneuemontreal_medium_italic
import busystatusbar.composeapp.generated.resources.ppneuemontreal_regular
import busystatusbar.composeapp.generated.resources.ppneuemontreal_semibold
import busystatusbar.composeapp.generated.resources.ppneuemontreal_semibold_italic
import busystatusbar.composeapp.generated.resources.ppneuemontreal_thin
import busystatusbar.composeapp.generated.resources.ppneuemontreal_thin_italic
import org.jetbrains.compose.resources.Font

val LocalBusyBarFonts = compositionLocalOf<BusyBarFonts> { error("No local pallet") }

data class BusyBarFonts(
    val ppNeueMontreal: FontFamily
)

@Composable
fun getBusyBarFonts() = BusyBarFonts(
    ppNeueMontreal = getPPNeueMontreal()
)


@Composable
private fun getPPNeueMontreal() = FontFamily(
    Font(
        resource = Res.font.ppneuemontreal_bold,
        weight = FontWeight.Bold,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.ppneuemontreal_bold_italic,
        weight = FontWeight.Bold,
        style = FontStyle.Italic
    ),
    Font(
        resource = Res.font.ppneuemontreal_italic,
        weight = FontWeight.Normal,
        style = FontStyle.Italic
    ),

    Font(
        resource = Res.font.ppneuemontreal_light,
        weight = FontWeight.Light,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.ppneuemontreal_light_italic,
        weight = FontWeight.Light,
        style = FontStyle.Italic
    ),

    Font(
        resource = Res.font.ppneuemontreal_medium,
        weight = FontWeight.Medium,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.ppneuemontreal_medium_italic,
        weight = FontWeight.Medium,
        style = FontStyle.Italic
    ),
    Font(
        resource = Res.font.ppneuemontreal_regular,
        weight = FontWeight.Normal,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.ppneuemontreal_semibold,
        weight = FontWeight.SemiBold,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.ppneuemontreal_semibold_italic,
        weight = FontWeight.SemiBold,
        style = FontStyle.Italic
    ),

    Font(
        resource = Res.font.ppneuemontreal_thin,
        weight = FontWeight.Thin,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.ppneuemontreal_thin_italic,
        weight = FontWeight.Thin,
        style = FontStyle.Italic
    ),
)