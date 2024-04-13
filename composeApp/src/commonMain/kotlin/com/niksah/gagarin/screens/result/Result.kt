package com.niksah.gagarin.screens.result

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
        }
    }
    when (state.value) {
        is ResultState.Content -> Content(
            onBack = viewModel::onBack,
            data = state.value as ResultState.Content
        )

        ResultState.Loading -> Loader()
    }
}

@Composable
expect fun Content(
    onBack: () -> Unit,
    data: ResultState.Content
)