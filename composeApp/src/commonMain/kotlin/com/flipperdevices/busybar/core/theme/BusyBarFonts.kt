package com.flipperdevices.busybar.core.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.*
import org.jetbrains.compose.resources.Font

val LocalBusyBarFonts = compositionLocalOf<BusyBarFonts> { error("No local pallet") }

data class BusyBarFonts(
    val ppNeueMontreal: FontFamily,
    val jetbrainsMono: FontFamily,
    val jetbrainsMonoNL: FontFamily
)

@Composable
fun getBusyBarFonts() = BusyBarFonts(
    ppNeueMontreal = getPPNeueMontreal(),
    jetbrainsMono = getJetBrainsMono(),
    jetbrainsMonoNL = getJetBrainsMonoNL()
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


@Composable
private fun getJetBrainsMono() = FontFamily(
    Font(
        resource = Res.font.jetbrainsmono_bold,
        weight = FontWeight.Bold,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.jetbrainsmono_bold_italic,
        weight = FontWeight.Bold,
        style = FontStyle.Italic
    ),
    Font(
        resource = Res.font.jetbrainsmono_italic,
        weight = FontWeight.Normal,
        style = FontStyle.Italic
    ),

    Font(
        resource = Res.font.jetbrainsmono_light,
        weight = FontWeight.Light,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.jetbrainsmono_light_italic,
        weight = FontWeight.Light,
        style = FontStyle.Italic
    ),

    Font(
        resource = Res.font.jetbrainsmono_medium,
        weight = FontWeight.Medium,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.jetbrainsmono_medium_italic,
        weight = FontWeight.Medium,
        style = FontStyle.Italic
    ),
    Font(
        resource = Res.font.jetbrainsmono_regular,
        weight = FontWeight.Normal,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.jetbrainsmono_semibold,
        weight = FontWeight.SemiBold,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.jetbrainsmono_semibold_italic,
        weight = FontWeight.SemiBold,
        style = FontStyle.Italic
    ),

    Font(
        resource = Res.font.jetbrainsmono_thin,
        weight = FontWeight.Thin,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.jetbrainsmono_thin_italic,
        weight = FontWeight.Thin,
        style = FontStyle.Italic
    ),
)


@Composable
private fun getJetBrainsMonoNL() = FontFamily(
    Font(
        resource = Res.font.jetbrainsmononl_bold,
        weight = FontWeight.Bold,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.jetbrainsmononl_bold_italic,
        weight = FontWeight.Bold,
        style = FontStyle.Italic
    ),
    Font(
        resource = Res.font.jetbrainsmononl_italic,
        weight = FontWeight.Normal,
        style = FontStyle.Italic
    ),

    Font(
        resource = Res.font.jetbrainsmononl_light,
        weight = FontWeight.Light,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.jetbrainsmononl_light_italic,
        weight = FontWeight.Light,
        style = FontStyle.Italic
    ),

    Font(
        resource = Res.font.jetbrainsmononl_medium,
        weight = FontWeight.Medium,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.jetbrainsmononl_medium_italic,
        weight = FontWeight.Medium,
        style = FontStyle.Italic
    ),
    Font(
        resource = Res.font.jetbrainsmononl_regular,
        weight = FontWeight.Normal,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.jetbrainsmononl_semibold,
        weight = FontWeight.SemiBold,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.jetbrainsmononl_semibold_italic,
        weight = FontWeight.SemiBold,
        style = FontStyle.Italic
    ),

    Font(
        resource = Res.font.jetbrainsmononl_thin,
        weight = FontWeight.Thin,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.jetbrainsmononl_thin_italic,
        weight = FontWeight.Thin,
        style = FontStyle.Italic
    ),
)