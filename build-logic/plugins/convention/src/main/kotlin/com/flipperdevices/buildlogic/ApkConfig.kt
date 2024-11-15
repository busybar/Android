package com.flipperdevices.buildlogic

import com.flipperdevices.buildlogic.model.FlavorType
import org.gradle.api.Project

object ApkConfig {
    const val APPLICATION_ID = "com.flipperdevices.busybar"

    const val MIN_SDK_VERSION = 26

    const val TARGET_SDK_VERSION = 35
    const val COMPILE_SDK_VERSION = 35

    private const val DEBUG_VERSION = "DEBUG_VERSION"

    val Project.VERSION_CODE
        get() = prop("version_code", 1).toInt()
    val Project.VERSION_NAME
        get() = prop("version_name", "1.0.$VERSION_CODE")

    val Project.COUNTLY_URL
        get() = prop("countly_url", "")
    val Project.COUNTLY_APP_KEY
        get() = prop("countly_app_key", "171c41398e2459b068869d6409047680896ed062")

    val Project.IS_GOOGLE_FEATURE_AVAILABLE
        get() = prop("is_google_feature", true).toBoolean()

    val Project.IS_METRIC_ENABLED
        get() = prop("is_metric_enabled", true).toBoolean()

    val Project.CURRENT_FLAVOR_TYPE: FlavorType
        get() {
            val default = FlavorType.DEV
            val key = "current_flavor_type"
            val propValue = propOrNull(key)
            if (propValue == null) {
                logger.warn("Property $key was not found, writing default $default")
            }
            return FlavorType.values().find { it.name == propValue } ?: default
        }
}

internal fun Project.propOrNull(key: String): String? {
    return providers.gradleProperty(key).orNull
}

internal fun Project.prop(key: String, default: Any): String {
    return providers.gradleProperty(key).getOrElse(default.toString())
}
