package com.flipperdevices.busybar.core.log

actual inline fun LogTagProvider.error(logMessage: () -> String) {
    println("[$TAG] ERROR: ${logMessage()}")
}

actual inline fun LogTagProvider.error(error: Throwable?, logMessage: () -> String) {
    println("[$TAG] ERROR: ${logMessage()} ${error?.message}")
}

actual inline fun LogTagProvider.info(logMessage: () -> String) {
    println("[$TAG] INFO: ${logMessage()}")
}

actual inline fun LogTagProvider.verbose(logMessage: () -> String) {
    println("[$TAG] VERBOSE: ${logMessage()}")
}

actual inline fun LogTagProvider.warn(logMessage: () -> String) {
    println("[$TAG] WARN: ${logMessage()}")
}

actual inline fun LogTagProvider.debug(logMessage: () -> String) {
    println("[$TAG] DEBUG: ${logMessage()}")
}

actual inline fun LogTagProvider.wtf(logMessage: () -> String) {
    println("[$TAG] WTF: ${logMessage()}")
}
