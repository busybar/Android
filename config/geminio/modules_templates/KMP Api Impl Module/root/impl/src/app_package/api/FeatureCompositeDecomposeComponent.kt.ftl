package ${packageName}.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.childStack
import ${packageName}.model.${__formattedModuleName}NavigationConfig
import com.flipperdevices.core.di.AppGraph
import com.flipperdevices.ui.decompose.DecomposeComponent
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@Inject
class ${__formattedModuleName}DecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
    private val ${__moduleName}ScreenDecomposeComponent: (ComponentContext) -> ${__formattedModuleName}ScreenDecomposeComponentImpl
) : ${__formattedModuleName}DecomposeComponent(),
    ComponentContext by componentContext {
    override val stack = childStack(
        source = navigation,
        serializer = ${__formattedModuleName}NavigationConfig.serializer(),
        initialConfiguration = ${__formattedModuleName}NavigationConfig.Main,
        handleBackButton = true,
        childFactory = ::child,
    )

    private fun child(
        config: ${__formattedModuleName}NavigationConfig,
        componentContext: ComponentContext
    ): DecomposeComponent = when (config) {
        ${__formattedModuleName}NavigationConfig.Main -> ${__moduleName}ScreenDecomposeComponent(componentContext)
    }


    @Inject
    @ContributesBinding(AppGraph::class, ${__formattedModuleName}DecomposeComponent.Factory::class)
    class Factory(
        private val factory: (
            componentContext: ComponentContext
        ) -> ${__formattedModuleName}DecomposeComponentImpl
    ) : ${__formattedModuleName}DecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext
        ) = factory(componentContext)
    }
}