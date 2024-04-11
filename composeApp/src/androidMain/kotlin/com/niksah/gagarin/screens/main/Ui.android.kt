package com.niksah.gagarin.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.darkrockstudios.libraries.mpfilepicker.FilePicker
import com.darkrockstudios.libraries.mpfilepicker.MPFile
import gagarinhak.composeapp.generated.resources.Res
import gagarinhak.composeapp.generated.resources.file_earmark_arrow_up
import gagarinhak.composeapp.generated.resources.file_scan
import gagarinhak.composeapp.generated.resources.load_file
import gagarinhak.composeapp.generated.resources.scan
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
actual fun FileChooser(
    showFilePicker: Boolean,
    fileType: List<String>,
    close: () -> Unit,
    loadFile: (MPFile<Any>?) -> Unit
) {
    FilePicker(show = showFilePicker, fileExtensions = fileType) { platformFile ->
        close()
        if (platformFile != null) {
            loadFile(platformFile)
        }
    }
}

@Composable
actual fun Content(onShowFilePicker: () -> Unit, onCamera: () -> Unit) {
    MobileUi(onShowFilePicker, onCamera)
}