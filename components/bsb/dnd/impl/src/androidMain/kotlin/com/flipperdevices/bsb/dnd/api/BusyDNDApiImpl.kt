package com.flipperdevices.bsb.dnd.api

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.provider.Settings
import com.flipperdevices.core.di.AppGraph
import com.flipperdevices.core.ktx.android.highlightSettingsTo
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding


@Inject
@ContributesBinding(AppGraph::class, BusyDNDApi::class)
class BusyDNDApiImpl(
    private val context: Context
) : BusyDNDApi {
    override fun tryToEnable() {
        val intent = Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
            .highlightSettingsTo(context.packageName)
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}