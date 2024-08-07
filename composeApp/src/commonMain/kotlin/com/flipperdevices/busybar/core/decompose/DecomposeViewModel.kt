package com.flipperdevices.busybar.core.decompose

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kotlinx.coroutines.Dispatchers

open class DecomposeViewModel : InstanceKeeper.Instance, LifecycleOwner {
    private val registry by lazy { LifecycleRegistry(Lifecycle.State.INITIALIZED) }
    override val lifecycle = registry

    val viewModelScope = this.coroutineScope(Dispatchers.Default)

    init {
        registry.onCreate()
        registry.onStart()
        registry.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        registry.onPause()
        registry.onStop()
        registry.onDestroy()
    }
}