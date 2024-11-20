import com.android.build.gradle.BaseExtension
import com.flipperdevices.buildlogic.ApkConfig

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.multiplatform")
    id("flipper.lint")
    id("org.jetbrains.kotlin.plugin.compose")
}

@Suppress("UnstableApiUsage")
configure<BaseExtension> {
    commonAndroid(project)

    defaultConfig {
        applicationId = ApkConfig.APPLICATION_ID
    }

    buildTypes {
        internal {
            isShrinkResources = true
            isMinifyEnabled = true
            consumerProguardFile(
                "proguard-rules.pro"
            )
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isShrinkResources = true
            isMinifyEnabled = true
            consumerProguardFile(
                "proguard-rules.pro"
            )
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

includeCommonKspConfigurationTo("ksp")
