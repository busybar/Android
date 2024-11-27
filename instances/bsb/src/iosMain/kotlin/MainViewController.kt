import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.bsb.App
import com.flipperdevices.bsb.di.IOSAppComponent
import com.flipperdevices.bsb.di.create
import com.russhwolf.settings.Settings
import com.russhwolf.settings.observable.makeObservable
import platform.UIKit.UIViewController

fun MainViewController(
    componentContext: ComponentContext,
    settings: Settings
): UIViewController {
    val appComponent = IOSAppComponent::class.create(settings.makeObservable())
    val rootComponent = appComponent.rootDecomposeComponentFactory(componentContext)
    return ComposeUIViewController {
        App(rootComponent, appComponent)
    }
}