package com.flipperdevices.busybar.search.model

import com.flipperdevices.busybar.search.permissions.Permission

sealed class SearchScreenState {
    data class RequestPermissions(val permissions: List<Permission>) : SearchScreenState()

    data class FoundingDevice(val devices: List<String>) : SearchScreenState()
}