package com.niksah.gagarin.screens.history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.niksah.gagarin.data.CurrentPlatform
import com.niksah.gagarin.data.Platform
import com.niksah.gagarin.utils.base.subscribeEvents
import com.niksah.gagarin.utils.views.Alert
import com.niksah.gagarin.utils.views.Loader
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.koin.koinViewModel

@Composable
fun HistoryUI() {
    var showError by remember { mutableStateOf("") }
    val viewModel = koinViewModel(HistoryViewModel::class)
    val state = viewModel.screenState.collectAsStateWithLifecycle()
    viewModel.subscribeEvents {
        when (it) {
            is HistoryEvent.Failure -> {
                showError = it.message
            }
        }
    }
    if (showError.isNotBlank()) {
        Alert(
            showError = showError,
            onDismissRequest = {
                showError = ""
                viewModel.fetch()
            }
        )
    }
    when (state.value) {
        is HistoryState.Content -> {
            when (CurrentPlatform.current) {
                Platform.Ios,
                Platform.Android -> MobileUi(
                    state = state.value as HistoryState.Content
                )

                Platform.Web,
                Platform.Pc -> FullScreenUi()
            }
        }

        HistoryState.Preparing -> Loader()
    }

}
