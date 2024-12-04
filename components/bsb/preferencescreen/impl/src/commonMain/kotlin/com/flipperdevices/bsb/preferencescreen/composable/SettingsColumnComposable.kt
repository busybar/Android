package com.flipperdevices.bsb.preferencescreen.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import busystatusbar.components.bsb.preferencescreen.impl.generated.resources.Res
import busystatusbar.components.bsb.preferencescreen.impl.generated.resources.preference_app_blocker_desc
import busystatusbar.components.bsb.preferencescreen.impl.generated.resources.preference_app_blocker_title
import busystatusbar.components.bsb.preferencescreen.impl.generated.resources.preference_auth
import busystatusbar.components.bsb.preferencescreen.impl.generated.resources.preference_notification_desc
import busystatusbar.components.bsb.preferencescreen.impl.generated.resources.preference_notification_title
import com.flipperdevices.bsb.preferencescreen.model.PreferenceScreenState
import com.flipperdevices.bsb.preferencescreen.model.SettingsAction
import com.flipperdevices.core.ktx.jre.clickableRipple

@Composable
fun SettingsColumnComposable(
    modifier: Modifier,
    screenState: PreferenceScreenState,
    onAction: (SettingsAction) -> Unit
) {
    Column(
        modifier
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            SettingItemComposable(
                modifier = Modifier,
                title = Res.string.preference_notification_title,
                description = Res.string.preference_notification_desc,
                enabled = screenState.isDndActive,
                onSwitch = { onAction(SettingsAction.SwitchDND(it)) }
            )

            SettingItemComposable(
                modifier = Modifier,
                title = Res.string.preference_app_blocker_title,
                description = Res.string.preference_app_blocker_desc,
                enabled = screenState.isAppBlockActive,
                onSwitch = { onAction(SettingsAction.SwitchAppBlocking(it)) }
            )

            if (screenState.devMode) {
                SettingItemComposable(
                    modifier = Modifier
                        .clickableRipple { onAction(SettingsAction.OpenAuth) },
                    title = Res.string.preference_auth,
                    description = null,
                    enabled = screenState.isAppBlockActive,
                    onSwitch = null
                )
            }
        }

        SettingsVersionComposable(
            Modifier,
            onActivateExpertMode = { onAction(SettingsAction.ExpertMode) }
        )
    }
}