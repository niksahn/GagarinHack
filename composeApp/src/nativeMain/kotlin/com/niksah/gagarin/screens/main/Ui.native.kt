package com.niksah.gagarin.screens.main

import androidx.compose.runtime.Composable
import com.darkrockstudios.libraries.mpfilepicker.MPFile

@Composable
actual fun FileChooser(
    showFilePicker: Boolean,
    fileType: List<String>,
    close: () -> Unit,
    loadFile: (MPFile<Any>?) -> Unit
) {
}

@Composable
actual fun Content(onShowFilePicker: () -> Unit, onCamera: () -> Unit) {
    MobileUi(onShowFilePicker, onCamera)
}