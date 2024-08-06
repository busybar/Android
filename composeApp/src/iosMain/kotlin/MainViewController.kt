import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.busybar.App
import com.flipperdevices.busybar.di.createAppComponent
import platform.UIKit.UIViewController

fun MainViewController(componentContext: ComponentContext): UIViewController {
    val appComponent = createAppComponent()
    val rootComponent = appComponent.rootComponent(componentContext)
    return ComposeUIViewController {
        App(rootComponent)
    }
}