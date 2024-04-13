package com.niksah.gagarin.screens.main

import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toFile
import com.darkrockstudios.libraries.mpfilepicker.FilePicker
import com.darkrockstudios.libraries.mpfilepicker.MPFile

data class AndroidFile(
    override val path: String,
    override val platformFile: Uri,
    val context: Context
) : MPFile<Uri> {
    override suspend fun getFileByteArray(): ByteArray = getFileBytes(this, context = context)
}

@Composable
actual fun FileChooser(
    showFilePicker: Boolean,
    fileType: List<String>,
    close: () -> Unit,
    loadFile: (MPFile<Any>?) -> Unit
) {

    val context = LocalContext.current
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenDocument()) { result ->
            if (result != null) {
                loadFile(AndroidFile(result.toString(), result, context))
            } else {
                loadFile(null)
            }
        }

    val mimeTypeMap = MimeTypeMap.getSingleton()
    val mimeTypes = if (fileType.isNotEmpty()) {
        fileType.mapNotNull { ext ->
            mimeTypeMap.getMimeTypeFromExtension(ext)
        }.toTypedArray()
    } else {
        emptyArray()
    }

    LaunchedEffect(showFilePicker) {
        if (showFilePicker) {
            launcher.launch(mimeTypes)
        }
    }
}
