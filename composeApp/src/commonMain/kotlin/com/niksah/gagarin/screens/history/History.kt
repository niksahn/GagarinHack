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
fun HistoryUI(
    onBack: () -> Unit,
    onItem: () -> Unit,
) {
    var showError by remember { mutableStateOf("") }
    val viewModel = koinViewModel(HistoryViewModel::class)
    val state = viewModel.screenState.collectAsStateWithLifecycle()
    viewModel.subscribeEvents {
        when (it) {
            is HistoryEvent.Failure -> {
                showError = it.message
            }

            HistoryEvent.OnItem -> onItem()
        }
    }
    if (showError.isNotBlank()) {
        Alert(
            showError = showError,
            onDismissRequest = {
                showError = ""
                onBack()
                //  viewModel.fetch()
            }
        )
    }
    if (state.value.isPrepairing && CurrentPlatform.current != Platform.Android) {
        Loader()
    } else {
        if (state.value.history.isEmpty() && !state.value.isPrepairing) {
            showError = "История пуста"
        }
        when (CurrentPlatform.current) {
            Platform.Ios,
            Platform.Android -> MobileUi(
                state = state.value,
                onItem = {
                    viewModel.onItem(it)
                },
                isLoading = state.value.isPrepairing,
                onRefresh = viewModel::fetch
            )

            Platform.Web,
            Platform.Pc -> FullScreenUi(
                state = state.value,
                onBack = onBack,
                onItem = {
                    viewModel.onItem(it)
                    onItem()
                }
            )
        }
    }
}
