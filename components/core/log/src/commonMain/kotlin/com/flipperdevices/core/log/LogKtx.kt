package com.flipperdevices.core.log

import com.flipperdevices.core.buildkonfig.BuildKonfig

inline fun error(logMessage: () -> String) {
    if (BuildKonfig.IS_LOG_ENABLED) {
        error(null, logMessage)
    }
}

inline fun error(error: Throwable, logMessage: () -> String) {
    if (BuildKonfig.IS_LOG_ENABLED) {
        error(null, error, logMessage)
    }
}

inline fun info(logMessage: () -> String) {
    if (BuildKonfig.IS_LOG_ENABLED) {
        info(null, logMessage)
    }
}

inline fun verbose(logMessage: () -> String) {
    if (BuildKonfig.IS_LOG_ENABLED) {
        verbose(null, logMessage)
    }
}

inline fun warn(logMessage: () -> String) {
    if (BuildKonfig.IS_LOG_ENABLED) {
        warn(null, logMessage)
    }
}

inline fun debug(logMessage: () -> String) {
    if (BuildKonfig.IS_LOG_ENABLED) {
        debug(null, logMessage)
    }
}

inline fun wtf(logMessage: () -> String) {
    if (BuildKonfig.IS_LOG_ENABLED) {
        wtf(null, logMessage)
    }
}
