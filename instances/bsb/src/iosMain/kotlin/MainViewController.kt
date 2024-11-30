import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.bsb.App
import com.flipperdevices.bsb.di.IOSAppComponent
import com.flipperdevices.bsb.di.create
import com.flipperdevices.core.ktx.jre.FlipperDispatchers
import com.russhwolf.settings.Settings
import com.russhwolf.settings.observable.makeObservable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import platform.UIKit.UIViewController

fun MainViewController(
    componentContext: ComponentContext,
    settings: Settings
): UIViewController {
    val applicationScope = CoroutineScope(
        SupervisorJob() + FlipperDispatchers.default
    )
    val appComponent = IOSAppComponent::class.create(
        settings.makeObservable(),
        applicationScope
    )
    val rootComponent = appComponent.rootDecomposeComponentFactory(
        componentContext,
        initialDeeplink = null
    )
    return ComposeUIViewController {
        App(rootComponent, appComponent)
    }
}