package com.flipperdevices.busybar.core.log

import timber.log.Timber

actual inline fun error(logMessage: () -> String) {
    Timber.e(logMessage.invoke())
}

actual inline fun error(error: Throwable, logMessage: () -> String) {
    Timber.e(error, logMessage.invoke())
}

actual inline fun info(logMessage: () -> String) {
    Timber.i(logMessage.invoke())
}

actual inline fun verbose(logMessage: () -> String) {
    Timber.v(logMessage.invoke())
}

actual inline fun warn(logMessage: () -> String) {
    Timber.w(logMessage.invoke())
}

actual inline fun debug(logMessage: () -> String) {
    Timber.d(logMessage.invoke())
}

actual inline fun wtf(logMessage: () -> String) {
    Timber.wtf(logMessage.invoke())
}
