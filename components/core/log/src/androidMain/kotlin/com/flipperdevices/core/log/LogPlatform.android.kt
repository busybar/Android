package com.flipperdevices.core.log

import timber.log.Timber

actual inline fun error(tag: String?, logMessage: () -> String) {
    if (tag == null) {
        Timber.e(logMessage.invoke())
    } else {
        Timber.tag(tag).e(logMessage.invoke())
    }
}

actual inline fun error(tag: String?, error: Throwable, logMessage: () -> String) {
    if (tag == null) {
        Timber.e(error, logMessage.invoke())
    } else {
        Timber.tag(tag).e(error, logMessage.invoke())
    }
}

actual inline fun info(tag: String?, logMessage: () -> String) {
    if (tag == null) {
        Timber.i(logMessage.invoke())
    } else {
        Timber.tag(tag).i(logMessage.invoke())
    }
}

actual inline fun verbose(tag: String?, logMessage: () -> String) {
    if (tag == null) {
        Timber.v(logMessage.invoke())
    } else {
        Timber.tag(tag).v(logMessage.invoke())
    }
}

actual inline fun warn(tag: String?, logMessage: () -> String) {
    if (tag == null) {
        Timber.w(logMessage.invoke())
    } else {
        Timber.tag(tag).w(logMessage.invoke())
    }
}

actual inline fun debug(tag: String?, logMessage: () -> String) {
    if (tag == null) {
        Timber.d(logMessage.invoke())
    } else {
        Timber.tag(tag).d(logMessage.invoke())
    }
}

actual inline fun wtf(tag: String?, logMessage: () -> String) {
    if (tag == null) {
        Timber.wtf(logMessage.invoke())
    } else {
        Timber.tag(tag).wtf(logMessage.invoke())
    }
}
