plugins {
    id("flipper.multiplatform-compose")
    id("flipper.anvil-multiplatform")
    id("kotlinx-serialization")
    id("flipper.multiplatform-dependencies")
}

commonDependencies {
    implementation(projects.components.bsb.auth.signup.api)

    implementation(projects.components.core.di)
    implementation(projects.components.core.ui.decompose)

    implementation(libs.decompose)
}