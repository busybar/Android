package com.flipperdevices.inappnotification.impl.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.flipperdevices.core.di.AppGraph
import com.flipperdevices.core.ui.lifecycle.viewModelWithFactoryWithoutRemember
import com.flipperdevices.inappnotification.api.InAppNotificationDecomposeComponent
import com.flipperdevices.inappnotification.impl.composable.ComposableInAppNotification
import com.flipperdevices.inappnotification.impl.viewmodel.InAppNotificationState
import com.flipperdevices.inappnotification.impl.viewmodel.InAppNotificationViewModel
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@Inject
class InAppNotificationDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
    private val viewModelFactory: (LifecycleOwner) -> InAppNotificationViewModel
) : InAppNotificationDecomposeComponent(componentContext) {
    private val viewModel = viewModelWithFactoryWithoutRemember(null) {
        viewModelFactory(this@InAppNotificationDecomposeComponentImpl)
    }

    @Composable
    override fun Render(modifier: Modifier) {
        val notificationState by viewModel.state().collectAsState()

        when (val localState = notificationState) {
            InAppNotificationState.NotPresent -> {}
            is InAppNotificationState.ShownNotification -> ComposableInAppNotification(
                notification = localState.notification,
                onNotificationHide = viewModel::onNotificationHide,
                modifier = modifier
            )
        }
    }

    @Inject
    @ContributesBinding(AppGraph::class, InAppNotificationDecomposeComponent.Factory::class)
    class Factory(
        private val factory: (ComponentContext) -> InAppNotificationDecomposeComponentImpl
    ) : InAppNotificationDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext
        ) = factory(componentContext)
    }
}
