package com.flipperdevices.core.log

import com.flipperdevices.core.buildkonfig.BuildKonfig

@Suppress("PropertyName")
interface LogTagProvider {
    @Suppress("VariableNaming")
    val TAG: String
}

inline fun LogTagProvider.error(logMessage: () -> String) {
    if (BuildKonfig.IS_LOG_ENABLED) {
        error(TAG, logMessage)
    }
}

inline fun LogTagProvider.error(error: Throwable?, logMessage: () -> String) {
    if (BuildKonfig.IS_LOG_ENABLED) {
        if (error == null) {
            error(TAG, logMessage)
        } else {
            error(TAG, error, logMessage)
        }
    }
}

inline fun LogTagProvider.info(logMessage: () -> String) {
    if (BuildKonfig.IS_LOG_ENABLED) {
        info(TAG, logMessage)
    }
}

inline fun LogTagProvider.verbose(logMessage: () -> String) {
    if (BuildKonfig.IS_LOG_ENABLED) {
        verbose(TAG, logMessage)
    }
}

inline fun LogTagProvider.warn(logMessage: () -> String) {
    if (BuildKonfig.IS_LOG_ENABLED) {
        warn(TAG, logMessage)
    }
}

inline fun LogTagProvider.debug(logMessage: () -> String) {
    if (BuildKonfig.IS_LOG_ENABLED) {
        debug(TAG, logMessage)
    }
}

inline fun LogTagProvider.wtf(logMessage: () -> String) {
    if (BuildKonfig.IS_LOG_ENABLED) {
        wtf(TAG, logMessage)
    }
}
