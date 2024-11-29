plugins {
    id("flipper.multiplatform")
    id("flipper.multiplatform-dependencies")
    id("flipper.anvil-multiplatform")
}

commonDependencies {
    implementation(projects.components.bsb.dnd.api)

    implementation(projects.components.core.di)
}

androidDependencies {
    implementation(projects.components.core.ktx)
}