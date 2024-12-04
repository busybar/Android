package com.flipperdevices.bsb.dnd.api

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.provider.Settings
import com.flipperdevices.bsb.preference.api.PreferenceApi
import com.flipperdevices.bsb.preference.api.get
import com.flipperdevices.bsb.preference.api.set
import com.flipperdevices.bsb.preference.model.SettingsEnum
import com.flipperdevices.core.activityholder.CurrentActivityHolder
import com.flipperdevices.core.activityholder.startActivity
import com.flipperdevices.core.di.AppGraph
import com.flipperdevices.core.ktx.android.highlightSettingsTo
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding


@Inject
@ContributesBinding(AppGraph::class, BusyDNDApi::class)
class BusyDNDApiImpl(
    private val context: Context,
    private val preferenceApi: PreferenceApi
) : BusyDNDApi {
    private val notificationManager by lazy { context.getSystemService(NotificationManager::class.java) }

    override fun isDNDSupportActive(): Boolean {
        if (!notificationManager.isNotificationPolicyAccessGranted) {
            return false
        }
        return preferenceApi.get(SettingsEnum.DND_SUPPORT, false)
    }

    override fun enableSupport(): Result<Unit> {
        if (!notificationManager.isNotificationPolicyAccessGranted) {
            requestDNDPermission()
            return Result.failure(RuntimeException("NotificationPolicyAccessGranted is false, so waiting action from user"))
        }
        preferenceApi.set(SettingsEnum.DND_SUPPORT, true)
        return Result.success(Unit)
    }

    override fun disableSupport() {
        preferenceApi.set(SettingsEnum.DND_SUPPORT, false)
    }

    private fun requestDNDPermission() {
        val intent = Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
            .highlightSettingsTo(context.packageName)

        CurrentActivityHolder.startActivity(intent, context)
    }
}