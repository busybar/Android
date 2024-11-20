import com.flipperdevices.buildlogic.ApkConfig.CURRENT_FLAVOR_TYPE

plugins {
    id("flipper.multiplatform")
    id("flipper.multiplatform-dependencies")
    alias(libs.plugins.buildkonfig)
}

buildConfig {
    className("BuildKonfig")
    packageName("${android.namespace}")
    useKotlinOutput { internalVisibility = false }
    buildConfigField(String::class.java, "PACKAGE", "${android.namespace}")
    buildConfigField(Boolean::class.java, "IS_LOG_ENABLED", CURRENT_FLAVOR_TYPE.isLogEnabled)
    buildConfigField(
        Boolean::class.java,
        "CRASH_APP_ON_FAILED_CHECKS",
        CURRENT_FLAVOR_TYPE.crashAppOnFailedChecks
    )
    buildConfigField(
        Boolean::class.java,
        "LOGGING_PENDING_COMMANDS",
        CURRENT_FLAVOR_TYPE.loggingPendingCommands
    )
}
