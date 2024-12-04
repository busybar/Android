plugins {
    id("flipper.multiplatform-compose")
    id("flipper.multiplatform-dependencies")
    id("flipper.anvil-multiplatform")
}

androidDependencies {
    implementation(projects.components.bsb.auth.within.onetap.api)

    implementation(projects.components.core.di)
    implementation(projects.components.core.ui.decompose)

    implementation(projects.components.bsb.auth.within.main.api)
    implementation(projects.components.bsb.auth.within.common)

    implementation(libs.decompose)
}
