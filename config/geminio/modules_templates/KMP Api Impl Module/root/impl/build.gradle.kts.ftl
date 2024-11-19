plugins {
<#if isScreenComponent || isCompositeComponent>
    id("flipper.multiplatform-compose")
    id("flipper.anvil-multiplatform")
<#else>
    id("flipper.multiplatform")
</#if>
<#if isCompositeComponent>
    id("kotlinx-serialization")
</#if>
    id("flipper.multiplatform-dependencies")
}

commonDependencies {
    implementation(projects.components.bsb.${__moduleName}.api)

<#if isScreenComponent || isCompositeComponent>
    implementation(projects.components.core.di)
    implementation(projects.components.core.ui.decompose)

    implementation(libs.decompose)
</#if>
}