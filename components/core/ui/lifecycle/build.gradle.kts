plugins {
    id("flipper.multiplatform-compose")
    id("flipper.multiplatform-dependencies")
}

commonDependencies {
    implementation(projects.components.core.ktx)
    implementation(projects.components.core.log)

    api(libs.decompose)
    implementation(libs.kotlin.coroutines)
    api(libs.essenty.lifecycle)
    implementation(libs.essenty.lifecycle.coroutines)
}