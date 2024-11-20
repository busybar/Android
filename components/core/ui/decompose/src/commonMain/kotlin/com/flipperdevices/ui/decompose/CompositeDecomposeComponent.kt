package com.flipperdevices.ui.decompose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.value.Value

abstract class CompositeDecomposeComponent<C : Any> : DecomposeComponent(), ComponentContext {
    protected val navigation = StackNavigation<C>()

    protected abstract val stack: Value<ChildStack<C, DecomposeComponent>>

    @Composable
    @Suppress("NonSkippableComposable")
    override fun Render(modifier: Modifier) {
        val childStack by stack.subscribeAsState()

        Children(
            modifier = modifier,
            stack = childStack,
        ) {
            it.instance.Render(Modifier)
        }
    }
}
