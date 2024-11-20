package ${packageName}.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.ui.decompose.ScreenDecomposeComponent
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class ${__formattedModuleName}ScreenDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext
) : ScreenDecomposeComponent(componentContext) {
    @Composable
    override fun Render(modifier: Modifier) {

    }
}