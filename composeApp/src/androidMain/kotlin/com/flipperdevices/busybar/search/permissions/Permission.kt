package com.flipperdevices.busybar.search.permissions

import dev.icerock.moko.permissions.Permission

fun fromMoko(permission: Permission): com.flipperdevices.busybar.search.permissions.Permission {
    return when(permission) {
        Permission.CAMERA -> com.flipperdevices.busybar.search.permissions.Permission.CAMERA
        Permission.GALLERY -> com.flipperdevices.busybar.search.permissions.Permission.GALLERY
        Permission.STORAGE -> com.flipperdevices.busybar.search.permissions.Permission.STORAGE
        Permission.WRITE_STORAGE -> com.flipperdevices.busybar.search.permissions.Permission.WRITE_STORAGE
        Permission.LOCATION -> com.flipperdevices.busybar.search.permissions.Permission.LOCATION
        Permission.COARSE_LOCATION -> com.flipperdevices.busybar.search.permissions.Permission.COARSE_LOCATION
        Permission.BACKGROUND_LOCATION -> com.flipperdevices.busybar.search.permissions.Permission.BACKGROUND_LOCATION
        Permission.BLUETOOTH_LE -> com.flipperdevices.busybar.search.permissions.Permission.BLUETOOTH_LE
        Permission.REMOTE_NOTIFICATION -> com.flipperdevices.busybar.search.permissions.Permission.REMOTE_NOTIFICATION
        Permission.RECORD_AUDIO -> com.flipperdevices.busybar.search.permissions.Permission.RECORD_AUDIO
        Permission.BLUETOOTH_SCAN -> com.flipperdevices.busybar.search.permissions.Permission.BLUETOOTH_SCAN
        Permission.BLUETOOTH_ADVERTISE -> com.flipperdevices.busybar.search.permissions.Permission.BLUETOOTH_ADVERTISE
        Permission.BLUETOOTH_CONNECT -> com.flipperdevices.busybar.search.permissions.Permission.BLUETOOTH_CONNECT
        Permission.CONTACTS -> com.flipperdevices.busybar.search.permissions.Permission.CONTACTS
        Permission.MOTION -> com.flipperdevices.busybar.search.permissions.Permission.MOTION
    }
}

fun toMoko(permission: com.flipperdevices.busybar.search.permissions.Permission): Permission {
    return when(permission) {
        com.flipperdevices.busybar.search.permissions.Permission.CAMERA -> Permission.CAMERA
        com.flipperdevices.busybar.search.permissions.Permission.GALLERY -> Permission.GALLERY
        com.flipperdevices.busybar.search.permissions.Permission.STORAGE -> Permission.STORAGE
        com.flipperdevices.busybar.search.permissions.Permission.WRITE_STORAGE -> Permission.WRITE_STORAGE
        com.flipperdevices.busybar.search.permissions.Permission.LOCATION -> Permission.LOCATION
        com.flipperdevices.busybar.search.permissions.Permission.COARSE_LOCATION -> Permission.COARSE_LOCATION
        com.flipperdevices.busybar.search.permissions.Permission.BACKGROUND_LOCATION -> Permission.BACKGROUND_LOCATION
        com.flipperdevices.busybar.search.permissions.Permission.BLUETOOTH_LE -> Permission.BLUETOOTH_LE
        com.flipperdevices.busybar.search.permissions.Permission.REMOTE_NOTIFICATION -> Permission.REMOTE_NOTIFICATION
        com.flipperdevices.busybar.search.permissions.Permission.RECORD_AUDIO -> Permission.RECORD_AUDIO
        com.flipperdevices.busybar.search.permissions.Permission.BLUETOOTH_SCAN -> Permission.BLUETOOTH_SCAN
        com.flipperdevices.busybar.search.permissions.Permission.BLUETOOTH_ADVERTISE -> Permission.BLUETOOTH_ADVERTISE
        com.flipperdevices.busybar.search.permissions.Permission.BLUETOOTH_CONNECT -> Permission.BLUETOOTH_CONNECT
        com.flipperdevices.busybar.search.permissions.Permission.CONTACTS -> Permission.CONTACTS
        com.flipperdevices.busybar.search.permissions.Permission.MOTION -> Permission.MOTION
    }
}