package com.flipperdevices.bsb.preferencescreen.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import busystatusbar.components.bsb.preferencescreen.impl.generated.resources.Res
import busystatusbar.components.bsb.preferencescreen.impl.generated.resources.preference_app_blocker_desc
import busystatusbar.components.bsb.preferencescreen.impl.generated.resources.preference_app_blocker_title
import busystatusbar.components.bsb.preferencescreen.impl.generated.resources.preference_notification_desc
import busystatusbar.components.bsb.preferencescreen.impl.generated.resources.preference_notification_title
import com.flipperdevices.bsb.preferencescreen.model.PreferenceScreenState

@Composable
fun SettingsColumnComposable(
    modifier: Modifier,
    screenState: PreferenceScreenState,
    onRequestDND: (Boolean) -> Unit,
    onAppBlock: (Boolean) -> Unit
) {
    Column(
        modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        SettingItemComposable(
            modifier = Modifier,
            title = Res.string.preference_notification_title,
            description = Res.string.preference_notification_desc,
            enabled = screenState.isDndActive,
            onSwitch = onRequestDND
        )

        SettingItemComposable(
            modifier = Modifier,
            title = Res.string.preference_app_blocker_title,
            description = Res.string.preference_app_blocker_desc,
            enabled = screenState.isAppBlockActive,
            onSwitch = onAppBlock
        )
    }
}