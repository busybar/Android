package com.flipperdevices.bsb.root.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.flipperdevices.bsb.preference.api.PreferenceScreenDecomposeComponent
import com.flipperdevices.bsb.root.model.RootNavigationConfig
import com.flipperdevices.core.di.AppGraph
import com.flipperdevices.ui.decompose.DecomposeComponent
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@Inject
class RootDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
    private val rootScreenDecomposeComponent: (ComponentContext) -> RootScreenDecomposeComponentImpl,
    private val preferenceDecomposeComponentFactory: PreferenceScreenDecomposeComponent.Factory
) : RootDecomposeComponent(),
    ComponentContext by componentContext {
    override val stack = childStack(
        source = navigation,
        serializer = RootNavigationConfig.serializer(),
        initialConfiguration = RootNavigationConfig.Main,
        handleBackButton = true,
        childFactory = ::child,
    )

    private fun child(
        config: RootNavigationConfig,
        componentContext: ComponentContext
    ): DecomposeComponent = when (config) {
        RootNavigationConfig.Main -> preferenceDecomposeComponentFactory(
            componentContext,
            onBackParameter = navigation::pop
        )
    }


    @Inject
    @ContributesBinding(AppGraph::class, RootDecomposeComponent.Factory::class)
    class Factory(
        private val factory: (
            componentContext: ComponentContext
        ) -> RootDecomposeComponentImpl
    ) : RootDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext
        ) = factory(componentContext)
    }
}