plugins {
    id("flipper.multiplatform-compose")
    id("flipper.anvil-multiplatform")
    id("kotlinx-serialization")
    id("flipper.multiplatform-dependencies")
}

commonDependencies {
    implementation(projects.components.bsb.timer.main.api)

    implementation(projects.components.core.di)
    implementation(projects.components.core.log)
    implementation(projects.components.core.ktx)
    implementation(projects.components.bsb.core.theme)
    implementation(projects.components.core.ui.decompose)
    implementation(projects.components.core.ui.lifecycle)

    implementation(projects.components.bsb.preference.api)
    implementation(projects.components.bsb.timer.setup.api)

    implementation(libs.decompose)
    implementation(libs.constraintlayout)
    implementation(libs.kotlin.datetime)
}