plugins {
    id("flipper.multiplatform-compose")
    id("flipper.anvil-multiplatform")
    id("flipper.multiplatform-dependencies")
}

commonDependencies {
    implementation(projects.components.bsb.auth.otp.screen.api)

    implementation(projects.components.core.di)
    implementation(projects.components.core.log)
    implementation(projects.components.core.ktx)
    implementation(projects.components.core.ui.decompose)
    implementation(projects.components.core.ui.lifecycle)
    implementation(projects.components.bsb.core.theme)
    implementation(projects.components.bsb.core.markdown)

    implementation(projects.components.bsb.auth.common)
    implementation(projects.components.bsb.auth.otp.element.api)

    implementation(libs.decompose)
}