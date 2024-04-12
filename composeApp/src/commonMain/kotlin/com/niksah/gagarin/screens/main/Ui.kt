package com.niksah.gagarin.screens.main

import androidx.compose.foundation.background
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.darkrockstudios.libraries.mpfilepicker.MPFile
import com.niksah.gagarin.utils.base.subscribeEvents
import com.niksah.gagarin.utils.base.subscribeScreenState
import com.niksah.gagarin.utils.views.Alert
import com.niksah.gagarin.utils.views.Loader
import com.niksah.seconHack.data.models.Operation
import moe.tlaster.precompose.koin.koinViewModel

@Composable
internal fun Main(
    onCamera: () -> Unit, onLoaded: () -> Unit
) {
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

            MainEvent.Loaded -> onLoaded()
        }
    }
    Content(
        onShowFilePicker = { showFilePicker = true }, onCamera = onCamera
    )
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

@Composable
expect fun Content(
    onShowFilePicker: () -> Unit,
    onCamera: () -> Unit,
)
