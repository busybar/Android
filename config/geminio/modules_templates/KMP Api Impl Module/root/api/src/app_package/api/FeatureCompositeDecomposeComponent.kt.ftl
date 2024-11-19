package ${packageName}.api

import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.ui.decompose.CompositeDecomposeComponent

abstract class ${__formattedModuleName}DecomposeComponent<C : Any> : CompositeDecomposeComponent<C>() {
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext
        ): ${__formattedModuleName}DecomposeComponent<*>
    }
}
