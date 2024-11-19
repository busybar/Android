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

    implementation(libs.decompose)
}