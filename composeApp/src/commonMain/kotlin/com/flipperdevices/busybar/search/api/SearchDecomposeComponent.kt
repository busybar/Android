package com.flipperdevices.busybar.search.api

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.flipperdevices.busybar.core.decompose.DecomposeComponent
import com.flipperdevices.busybar.core.decompose.viewModelWithFactory
import com.flipperdevices.busybar.root.api.RootNavigationApi
import com.flipperdevices.busybar.search.composable.SearchScreenComposable
import com.flipperdevices.busybar.search.viewmodel.SearchViewModel
import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

const val PREFS_SEARCH_SKIP = "skip_search"

@Inject
class SearchDecomposeComponent(
    @Assisted componentContext: ComponentContext,
    @Assisted private val navigationApi: RootNavigationApi,
    private val settings: Settings,
    private val searchViewModelFactory: (permissionsController: PermissionsController) -> SearchViewModel
) : DecomposeComponent, ComponentContext by componentContext {


    @Composable
    override fun Render(modifier: Modifier) {
        val permissionsControllerFactory = rememberPermissionsControllerFactory()
        val viewModel = viewModelWithFactory(null) {
            searchViewModelFactory(permissionsControllerFactory.createPermissionsController())
        }
        BindEffect(viewModel.permissionsController)

        val searchScreenState by viewModel.getState().collectAsState()
        SearchScreenComposable(
            modifier = Modifier.systemBarsPadding()
                .statusBarsPadding(),
            searchScreenState = searchScreenState,
            onInvalidate = viewModel::invalidate,
            onDemo = {
                settings[PREFS_SEARCH_SKIP] = true
                navigationApi.openRootScreen()
            }
        )
    }
}