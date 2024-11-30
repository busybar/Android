package com.flipperdevices.bsb.appblocker.listener

import android.app.usage.UsageEvents
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import com.flipperdevices.bsb.appblocker.deeplink.AppBlockDeeplinkParser
import com.flipperdevices.core.di.AndroidPlatformDependencies
import com.flipperdevices.core.di.AppGraph
import com.flipperdevices.core.ktx.jre.withLock
import com.flipperdevices.core.log.LogTagProvider
import com.flipperdevices.core.log.info
import com.flipperdevices.core.log.verbose
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

const val APP_LOCK_LOOP_INTERVAL = 500L
const val APP_LOCK_CHECK_INTERVAL = 1000L

@Inject
class UsageStatsLooper(
    private val context: Context,
    private val scope: CoroutineScope,
    private val androidPlatformDependencies: AndroidPlatformDependencies
) : LogTagProvider {
    override val TAG = "UsageStatsLooper"

    private val statsManager by lazy { context.getSystemService(UsageStatsManager::class.java) }
    private val mutex = Mutex()
    private var job: Job? = null

    fun startLoop() = scope.launch {
        withLock(mutex, "start") {
            job?.cancelAndJoin()
            job = scope.launch {
                while (isActive) {
                    loop()
                    delay(APP_LOCK_LOOP_INTERVAL)
                }
            }
        }
    }

    fun stopLoop() = scope.launch {
        withLock(mutex, "stop") {
            job?.cancelAndJoin()
            job = null
        }
    }

    private suspend fun loop() {
        val now = System.currentTimeMillis()
        val events = statsManager.queryEvents(now - APP_LOCK_CHECK_INTERVAL, now)

        val event = UsageEvents.Event()
        while (events.hasNextEvent()) {
            events.getNextEvent(event)
            verbose { "Process $event" }
            checkEvent(event)
        }
    }

    private suspend fun checkEvent(event: UsageEvents.Event) {
        if (event.eventType != UsageEvents.Event.ACTIVITY_RESUMED) {
            return // Not activity event
        }

        if (event.packageName == context.packageName) {
            return // Busy application
        }

        info { "Detect forbidden app" }

        val intent = AppBlockDeeplinkParser.getIntent(
            context,
            event.packageName,
            androidPlatformDependencies.splashScreenActivity,
        )
        context.startActivity(intent)
    }
}