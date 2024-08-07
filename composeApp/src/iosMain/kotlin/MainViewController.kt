import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.busybar.App
import com.flipperdevices.busybar.di.createAppComponent
import com.russhwolf.settings.Settings
import platform.UIKit.UIViewController

fun MainViewController(
    componentContext: ComponentContext,
    settings: Settings
): UIViewController {
    val appComponent = createAppComponent(settings)
    val rootComponent = appComponent.rootComponent(componentContext)
    return ComposeUIViewController {
        App(rootComponent)
    }
}