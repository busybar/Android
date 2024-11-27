plugins {
    id("flipper.multiplatform")
    id("flipper.multiplatform-dependencies")
    id("flipper.anvil-multiplatform")
}

commonDependencies {
    implementation(projects.components.bsb.timer.background.api)

    implementation(projects.components.core.di)
    implementation(projects.components.core.ktx)
    implementation(projects.components.core.log)

    implementation(libs.kotlin.coroutines)
    implementation(libs.kotlin.datetime)
}

androidDependencies {
    implementation(libs.androidx.lifecycle)
}

commonTestDependencies {
    implementation(libs.kotlin.test)
}