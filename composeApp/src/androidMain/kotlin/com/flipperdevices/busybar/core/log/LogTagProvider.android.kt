package com.flipperdevices.busybar.core.log

import timber.log.Timber

actual inline fun LogTagProvider.error(logMessage: () -> String) {
    Timber.tag(TAG).e(logMessage.invoke())
}

actual inline fun LogTagProvider.error(error: Throwable?, logMessage: () -> String) {
    if (error == null) {
        Timber.tag(TAG).e(logMessage.invoke())
    } else {
        Timber.tag(TAG).e(error, logMessage.invoke())
    }
}

actual inline fun LogTagProvider.info(logMessage: () -> String) {
    Timber.tag(TAG).i(logMessage.invoke())
}

actual inline fun LogTagProvider.verbose(logMessage: () -> String) {
    Timber.tag(TAG).v(logMessage.invoke())

}

actual inline fun LogTagProvider.warn(logMessage: () -> String) {
    Timber.tag(TAG).w(logMessage.invoke())
}

actual inline fun LogTagProvider.debug(logMessage: () -> String) {
    Timber.tag(TAG).d(logMessage.invoke())
}

actual inline fun LogTagProvider.wtf(logMessage: () -> String) {
    Timber.tag(TAG).wtf(logMessage.invoke())
}
