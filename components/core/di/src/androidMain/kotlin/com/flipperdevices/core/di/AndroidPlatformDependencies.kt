package com.flipperdevices.core.di

import android.app.Activity
import kotlin.reflect.KClass

class AndroidPlatformDependencies(
    val splashScreenActivity: KClass<out Activity>
) : PlatformDependencies
