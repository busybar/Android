plugins {
<#if isScreenComponent || isCompositeComponent>
    id("flipper.multiplatform-compose")
<#else>
    id("flipper.multiplatform")
</#if>
    id("flipper.multiplatform-dependencies")
}

commonDependencies {
<#if isScreenComponent || isCompositeComponent>
    implementation(libs.decompose)
    implementation(projects.components.core.ui.decompose)
</#if>
}