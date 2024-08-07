package com.flipperdevices.busybar.search.viewmodel

import com.flipperdevices.busybar.core.decompose.DecomposeViewModel
import com.flipperdevices.busybar.search.model.SearchScreenState
import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionsController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class SearchViewModel(
    @Assisted val permissionsController: PermissionsController
) : DecomposeViewModel() {
    private val stateFlow = MutableStateFlow<SearchScreenState>(
        SearchScreenState.FoundingDevice(
            emptyList()
        )
    )

    private val mutex = Mutex()

    init {
        invalidate()
    }

    fun getState() = stateFlow.asStateFlow()

    fun invalidate() = viewModelScope.launch {
        mutex.withLock {
            val permissionsMissing = listOf(
                Permission.BLUETOOTH_LE, Permission.BLUETOOTH_SCAN
            ).filter {
                !permissionsController.isPermissionGranted(it)
            }
            if (permissionsMissing.isNotEmpty()) {
                stateFlow.emit(SearchScreenState.RequestPermissions(permissionsMissing))
                permissionsMissing.forEach {
                    try {
                        permissionsController.providePermission(it)
                    } catch (deniedAlways: DeniedAlwaysException) {
                        permissionsController.openAppSettings()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                return@withLock
            }
            stateFlow.emit(SearchScreenState.FoundingDevice(emptyList()))
        }
    }
}