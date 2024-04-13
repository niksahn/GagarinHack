package com.niksah.gagarin.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.darkrockstudios.libraries.mpfilepicker.MPFile
import com.niksah.gagarin.data.CurrentPlatform
import com.niksah.gagarin.data.Platform
import com.niksah.gagarin.utils.base.subscribeEvents
import com.niksah.gagarin.utils.base.subscribeScreenState
import com.niksah.gagarin.utils.views.Alert
import com.niksah.gagarin.utils.views.Loader
import com.niksah.seconHack.data.models.Operation
import gagarinhak.composeapp.generated.resources.Res
import gagarinhak.composeapp.generated.resources.file_loaded
import kotlinx.coroutines.delay
import moe.tlaster.precompose.koin.koinViewModel
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun Main(
    onCamera: () -> Unit,
    onLoaded: () -> Unit,
    onHistory: () -> Unit
) {
    val fileLoaded = stringResource(Res.string.file_loaded)
    val viewModel = koinViewModel(MainViewModel::class)
    var showFilePicker by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf("") }
    val fileType = listOf("jpg", "png")
    val state = viewModel.subscribeScreenState()
    FileChooser(showFilePicker = showFilePicker,
        fileType = fileType,
        close = { showFilePicker = false },
        loadFile = {
            showFilePicker = false
            if (it != null) {
                viewModel.loadFile(it)
            }
        })

    viewModel.subscribeEvents {
        when (it) {
            is MainEvent.Failure -> {
                showError = it.message
            }

            MainEvent.Loaded -> {
                showError = fileLoaded
                delay(1000)
                onLoaded()
            }
        }
    }
    when (CurrentPlatform.current) {
        Platform.Ios,
        Platform.Android -> MobileUi(
            onShowFilePicker = { showFilePicker = true },
            onCamera = onCamera,
        )

        Platform.Web,
        Platform.Pc -> FullScreenUi(
            onShowFilePicker = { showFilePicker = true },
            onHistory = onHistory,
        )
    }
    if (showError.isNotBlank()) {
        Alert(
            showError = showError,
            onDismissRequest = { showError = "" }
        )
    }
    if (state.value.operation is Operation.Preparing) {
        Loader()
    }
}

@Composable
expect fun FileChooser(
    showFilePicker: Boolean,
    fileType: List<String>,
    close: () -> Unit,
    loadFile: (MPFile<Any>?) -> Unit
)

