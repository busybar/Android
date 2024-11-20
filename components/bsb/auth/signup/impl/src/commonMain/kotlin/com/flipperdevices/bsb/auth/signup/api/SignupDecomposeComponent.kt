package com.flipperdevices.bsb.auth.signup.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.childStack
import com.flipperdevices.bsb.auth.signup.model.SignupNavigationConfig
import com.flipperdevices.core.di.AppGraph
import com.flipperdevices.ui.decompose.DecomposeComponent
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

@Inject
class SignupDecomposeComponentImpl(
    @Assisted componentContext: ComponentContext,
    private val signupScreenDecomposeComponent: (ComponentContext) -> SignupScreenDecomposeComponentImpl
) : SignupDecomposeComponent<SignupNavigationConfig>(),
    ComponentContext by componentContext {
    override val stack = childStack(
        source = navigation,
        serializer = SignupNavigationConfig.serializer(),
        initialConfiguration = SignupNavigationConfig.Main,
        handleBackButton = true,
        childFactory = ::child,
    )

    private fun child(
        config: SignupNavigationConfig,
        componentContext: ComponentContext
    ): DecomposeComponent = when (config) {
        SignupNavigationConfig.Main -> signupScreenDecomposeComponent(componentContext)
    }

    @Inject
    @ContributesBinding(AppGraph::class, SignupDecomposeComponent.Factory::class)
    class Factory(
        private val factory: (
            componentContext: ComponentContext
        ) -> SignupDecomposeComponentImpl
    ) : SignupDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext
        ) = factory(componentContext)
    }
}