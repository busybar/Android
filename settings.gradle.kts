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
    ":components:core:log",
    ":components:core:ktx",
    ":components:core:build-konfig",
    ":components:core:ui:decompose",
    ":components:core:ui:lifecycle",

    ":components:bsb:core:theme",
    ":components:bsb:core:res",

    ":components:bsb:root:api",
    ":components:bsb:root:impl",
    ":components:bsb:preference:api",
    ":components:bsb:preference:impl",
    ":components:bsb:cloud:api",
    ":components:bsb:cloud:impl",

    ":components:bsb:auth:common",
    ":components:bsb:auth:main:impl",
    ":components:bsb:auth:main:api",
    ":components:bsb:auth:main:impl",
    ":components:bsb:auth:login:api",
    ":components:bsb:auth:login:impl",
    ":components:bsb:auth:signup:api",
    ":components:bsb:auth:signup:impl",

    ":components:bsb:timer:setup:api",
    ":components:bsb:timer:setup:impl",
    ":components:bsb:timer:main:api",
    ":components:bsb:timer:main:impl",
    ":components:bsb:timer:background:api",
    ":components:bsb:timer:background:impl",
)
