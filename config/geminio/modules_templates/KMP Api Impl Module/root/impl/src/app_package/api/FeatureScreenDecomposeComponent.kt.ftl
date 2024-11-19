package ${packageName}.api

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.core.di.AppGraph
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@Inject
class ${__formattedModuleName}ScreenDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
) : ${__formattedModuleName}ScreenDecomposeComponent(componentContext) {
    @Composable
    override fun Render() {

    }

    @Inject
    @ContributesBinding(AppGraph::class, ${__formattedModuleName}ScreenDecomposeComponent.Factory::class)
    class Factory(
        private val factory: (
            componentContext: ComponentContext
        ) -> ${__formattedModuleName}ScreenDecomposeComponentImpl
    ) : ${__formattedModuleName}ScreenDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext
        ) = factory(componentContext)
    }
}