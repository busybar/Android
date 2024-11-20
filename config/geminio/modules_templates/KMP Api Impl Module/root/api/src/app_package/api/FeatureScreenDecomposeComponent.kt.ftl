package ${packageName}.api

import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.ui.decompose.ScreenDecomposeComponent

abstract class ${__formattedModuleName}ScreenDecomposeComponent(
    componentContext: ComponentContext
) : ScreenDecomposeComponent(componentContext) {
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
        ): ${__formattedModuleName}ScreenDecomposeComponent
    }
}
