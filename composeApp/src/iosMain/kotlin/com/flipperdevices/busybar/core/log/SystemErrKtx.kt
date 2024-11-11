package com.flipperdevices.busybar.core.log

actual inline fun error(logMessage: () -> String) {
    println("ERROR: ${logMessage()}")
}

actual inline fun error(error: Throwable, logMessage: () -> String) {
    println("ERROR: ${logMessage()} ${error.message}")
}

actual inline fun info(logMessage: () -> String) {
    println("INFO: ${logMessage()}")
}

actual inline fun verbose(logMessage: () -> String) {
    println("VERBOSE: ${logMessage()}")
}

actual inline fun warn(logMessage: () -> String) {
    println("WARN: ${logMessage()}")
}

actual inline fun debug(logMessage: () -> String) {
    println("DEBUG: ${logMessage()}")
}

actual inline fun wtf(logMessage: () -> String) {
    println("WTF: ${logMessage()}")
}
