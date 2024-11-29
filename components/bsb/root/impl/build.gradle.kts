plugins {
    id("flipper.multiplatform-compose")
    id("flipper.multiplatform-dependencies")
    id("flipper.anvil-multiplatform")
    id("kotlinx-serialization")
}

commonDependencies {
    implementation(projects.components.bsb.root.api)

    implementation(projects.components.core.di)
    implementation(projects.components.core.ui.decompose)

    implementation(projects.components.bsb.preference.api)
    implementation(projects.components.bsb.auth.main.api)
    implementation(projects.components.bsb.timer.main.api)
    implementation(projects.components.bsb.preferencescreen.api)

    implementation(libs.decompose)
    implementation(libs.decompose.composeExtension)
}