package com.niksah.gagarin.screens.result

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.niksah.gagarin.data.CurrentPlatform
import com.niksah.gagarin.data.Platform
import com.niksah.gagarin.utils.base.subscribeEvents
import com.niksah.gagarin.utils.base.subscribeScreenState
import com.niksah.gagarin.utils.views.Loader
import moe.tlaster.precompose.koin.koinViewModel

@Composable
fun ResultUi(
    onBack: () -> Unit
) {
    val viewModel = koinViewModel(ResultViewModel::class)
    val state = viewModel.subscribeScreenState()
    viewModel.subscribeEvents {
        when (it) {
            ResultEvent.GoBack -> onBack()
            is ResultEvent.Failure -> {}
        }
    }
    when (state.value) {
        is ResultState.Content ->
            when (CurrentPlatform.current) {
                Platform.Ios,
                Platform.Android -> MobileUI(
                    onBack = viewModel::onBack,
                    data = state.value as ResultState.Content,
                    onSave = viewModel::save
                )

                Platform.Web,
                Platform.Pc -> FullScreenUi(
                    onBack = viewModel::onBack,
                    data = state.value as ResultState.Content,
                    onSave = viewModel::save
                )
            }

        ResultState.Loading -> Loader()
    }
}
