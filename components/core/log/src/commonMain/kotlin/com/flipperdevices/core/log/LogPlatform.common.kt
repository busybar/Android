package com.flipperdevices.core.log

expect inline fun error(tag: String?, logMessage: () -> String)

expect inline fun error(tag: String?, error: Throwable, logMessage: () -> String)

expect inline fun info(tag: String?, logMessage: () -> String)

expect inline fun verbose(tag: String?, logMessage: () -> String)

expect inline fun warn(tag: String?, logMessage: () -> String)

expect inline fun debug(tag: String?, logMessage: () -> String)

expect inline fun wtf(tag: String?, logMessage: () -> String)
