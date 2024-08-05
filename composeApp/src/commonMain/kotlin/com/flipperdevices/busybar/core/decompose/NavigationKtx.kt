package com.flipperdevices.busybar.core.decompose

import com.arkivanov.decompose.router.stack.StackNavigator
import com.arkivanov.decompose.router.stack.pop

inline fun <C : Any> StackNavigator<C>.popOr(
    crossinline fallback: () -> Unit = {},
) = pop { onComplete ->
    if (!onComplete) {
        fallback()
    }
}
