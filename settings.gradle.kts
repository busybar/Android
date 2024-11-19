rootProject.name = "BusyStatusBar"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")

    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(
    ":instances:bsb",

    ":components:core:di",
    ":components:core:activityholder",
    ":components:core:ui:decompose",

    ":components:bsb:core:theme",

    ":components:bsb:root:api",
    ":components:bsb:root:impl",
    ":components:bsb:preference:api",
    ":components:bsb:preference:impl",
)