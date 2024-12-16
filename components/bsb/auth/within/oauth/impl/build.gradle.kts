plugins {
    id("flipper.multiplatform-compose")
    id("flipper.anvil-multiplatform")
    id("flipper.multiplatform-dependencies")
}

androidDependencies {
    implementation(projects.components.bsb.auth.within.oauth.api)

    implementation(projects.components.core.di)
    implementation(projects.components.core.log)
    implementation(projects.components.core.ui.decompose)
    implementation(projects.components.core.ui.lifecycle)
    implementation(projects.components.bsb.core.theme)

    implementation(projects.components.bsb.auth.within.main.api)
    implementation(projects.components.bsb.auth.within.common)

    implementation(projects.components.bsb.cloud.api)

    implementation(libs.decompose)
    implementation(libs.webview)
}