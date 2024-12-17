package com.flipperdevices.inappnotification.impl.viewmodel

import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.flipperdevices.core.log.LogTagProvider
import com.flipperdevices.core.ui.lifecycle.DecomposeViewModel
import com.flipperdevices.inappnotification.api.InAppNotificationListener
import com.flipperdevices.inappnotification.api.InAppNotificationStorage
import com.flipperdevices.inappnotification.api.model.InAppNotification
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class InAppNotificationViewModel(
    @Assisted private val lifecycleOwner: LifecycleOwner,
    private val notificationStorage: InAppNotificationStorage
) : DecomposeViewModel(), InAppNotificationListener, LogTagProvider {
    override val TAG = "InAppNotificationViewModel"

    private val notificationState = MutableStateFlow<InAppNotificationState>(
        InAppNotificationState.NotPresent
    )

    private val lifecycleCallback = object : Lifecycle.Callbacks {
        override fun onResume() {
            viewModelScope.launch { notificationStorage.subscribe(this@InAppNotificationViewModel) }
        }

        override fun onPause() {
            viewModelScope.launch { notificationStorage.unsubscribe() }
        }
    }

    init {
        lifecycleOwner.lifecycle.subscribe(lifecycleCallback)
    }

    fun state(): StateFlow<InAppNotificationState> = notificationState

    fun onNotificationHide(notification: InAppNotification) {
        notificationState.update {
            if (it is InAppNotificationState.ShownNotification &&
                it.notification == notification
            ) {
                InAppNotificationState.NotPresent
            } else {
                it
            }
        }
    }

    override suspend fun onNewNotification(notification: InAppNotification) {
        notificationState.emit(InAppNotificationState.ShownNotification(notification))
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleOwner.lifecycle.unsubscribe(lifecycleCallback)
    }
}

sealed class InAppNotificationState {
    data object NotPresent : InAppNotificationState()
    data class ShownNotification(
        val notification: InAppNotification
    ) : InAppNotificationState()
}
