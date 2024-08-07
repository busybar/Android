package com.flipperdevices.busybar.settings.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busystatusbar.composeapp.generated.resources.Res
import busystatusbar.composeapp.generated.resources.device_section_about_dev_mode_title
import busystatusbar.composeapp.generated.resources.device_section_about_version_title
import busystatusbar.composeapp.generated.resources.device_section_about_version_value
import busystatusbar.composeapp.generated.resources.ic_back
import busystatusbar.composeapp.generated.resources.search_demo_btn
import busystatusbar.composeapp.generated.resources.settings_dark_theme
import busystatusbar.composeapp.generated.resources.settings_firmware_update
import busystatusbar.composeapp.generated.resources.settings_forget
import com.flipperdevices.busybar.core.ktx.clickableRipple
import com.flipperdevices.busybar.core.theme.LocalBusyBarFonts
import com.flipperdevices.busybar.core.theme.LocalPallet
import com.flipperdevices.busybar.device.composable.AboutDeviceRowComposable
import com.flipperdevices.busybar.settings.model.SettingsEnum
import com.flipperdevices.busybar.settings.model.SettingsState
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SettingsScreenComposable(
    modifier: Modifier,
    settingsState: SettingsState,
    onBack: () -> Unit,
    onChange: (SettingsEnum, Boolean) -> Unit,
    onForgetDevice: () -> Unit
) = Column(
    modifier = modifier.fillMaxHeight(),
    horizontalAlignment = Alignment.CenterHorizontally,
) {
    ScreenComposableBar(onBack)

    Box(Modifier.weight(1f)) {
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            AboutDeviceRowComposable(
                Res.string.device_section_about_version_title,
                Res.string.device_section_about_version_value
            )

            Line()

            BooleanRowComposable(
                Res.string.device_section_about_dev_mode_title,
                settingsState.devMode,
                onChange = { onChange(SettingsEnum.DEV_MODE, it) }
            )

            Line()

            BooleanRowComposable(
                Res.string.settings_firmware_update,
                settingsState.automaticFirmwareUpdate,
                onChange = { onChange(SettingsEnum.AUTO_UPDATE, it) }
            )
            Line()
            BooleanRowComposable(
                Res.string.settings_dark_theme,
                settingsState.darkTheme,
                onChange = { onChange(SettingsEnum.DARK_THEME, it) }
            )
        }
    }

    Text(
        modifier = Modifier.fillMaxWidth()
            .clickableRipple(onClick = onForgetDevice)
            .padding(vertical = 14.dp, horizontal = 12.dp),
        text = stringResource(Res.string.settings_forget),
        fontSize = 18.sp,
        fontFamily = LocalBusyBarFonts.current.ppNeueMontreal,
        fontWeight = FontWeight.W500,
        color = LocalPallet.current.danger.primary,
        textAlign = TextAlign.Center
    )
}


@Composable
private fun BooleanRowComposable(
    key: StringResource, value: Boolean, onChange: (Boolean) -> Unit
) {
    Row(
        Modifier.clickableRipple { onChange(!value) }.padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(key),
            fontSize = 16.sp,
            fontFamily = LocalBusyBarFonts.current.jetbrainsMono,
            fontWeight = FontWeight.W400,
            color = LocalPallet.current.invert.black,
            letterSpacing = 0.48.sp,
        )


        Switch(
            checked = value,
            onCheckedChange = onChange,
            colors = SwitchDefaults.colors(
                checkedTrackColor = LocalPallet.current.brand.secondary,
                checkedThumbColor = LocalPallet.current.brand.primary,
                uncheckedTrackColor = LocalPallet.current.brand.primary,
                uncheckedThumbColor = LocalPallet.current.onColor.white
            )
        )
    }
}

@Composable
private fun Line() {
    Box(
        Modifier.fillMaxWidth().height(1.dp).background(LocalPallet.current.neutral.quaternary)
    )
}


@Composable
fun ScreenComposableBar(onBack: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().height(30.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.padding(start = 14.dp).size(24.dp)
                .clickableRipple(onClick = onBack),
            painter = painterResource(Res.drawable.ic_back),
            contentDescription = null,
            tint = LocalPallet.current.neutral.tertiary
        )
    }
}