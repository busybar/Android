plugins {
    id("flipper.multiplatform-compose")
    id("flipper.multiplatform-dependencies")
}

commonDependencies {
    implementation(projects.components.core.ktx)
    implementation(projects.components.bsb.core.theme)
}

compose.resources {
    publicResClass = true
}