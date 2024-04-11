package com.niksah.gagarin.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darkrockstudios.libraries.mpfilepicker.MPFile
import gagarinhak.composeapp.generated.resources.Res
import gagarinhak.composeapp.generated.resources.doc_scanner
import gagarinhak.composeapp.generated.resources.file_earmark_arrow_up
import gagarinhak.composeapp.generated.resources.load_file
import gagarinhak.composeapp.generated.resources.load_file_
import kotlinx.browser.document
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Uint8Array
import org.khronos.webgl.get
import org.w3c.dom.Document
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.ItemArrayLike
import org.w3c.dom.asList
import org.w3c.files.File
import org.w3c.files.FileReader
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

typealias FileSelected = (MPFile<Any>?) -> Unit

class PlatformFile(override val path: String, override val platformFile: File) : MPFile<File> {
    override suspend fun getFileByteArray(): ByteArray = suspendCoroutine {
        val reader = FileReader()
        reader.onload = { loadEvt ->
            try {
                val eventFileReader = loadEvt.target?.let { it as FileReader }!!
                val content = eventFileReader.result as ArrayBuffer
                val array = Uint8Array(content)

                val fileByteArray = ByteArray(array.length)
                for (i in 0 until array.length) {
                    fileByteArray[i] = array[i]
                }
                it.resumeWith(Result.success(fileByteArray))
            } catch (e: Throwable) {
                it.resumeWithException(e)
            }
        }
        reader.readAsArrayBuffer(platformFile)
    }
}

@Composable
actual fun FileChooser(
    showFilePicker: Boolean,
    fileType: List<String>,
    close: () -> Unit,
    loadFile: FileSelected
) {
    LaunchedEffect(showFilePicker) {
        if (showFilePicker) {
            val fixedExtensions = fileType.map { ".$it" }
            val file: List<File> =
                document.selectFilesFromDisk(fixedExtensions.joinToString(","), false)
            val platformFile = PlatformFile(file.first().name, file.first())
            loadFile(platformFile)
        }
    }
}

suspend fun Document.selectFilesFromDisk(
    accept: String,
    isMultiple: Boolean
): List<File> = suspendCoroutine {
    val tempInput = (createElement("input") as HTMLInputElement).apply {
        type = "file"
        style.display = "none"
        this.accept = accept
        multiple = isMultiple
    }

    tempInput.onchange = { changeEvt ->
        val files = (changeEvt.target.asDynamic().files as ItemArrayLike<File>).asList()
        it.resume(files)
    }

    body!!.append(tempInput)
    tempInput.click()
    tempInput.remove()
}

@Composable
actual fun Content(onShowFilePicker: () -> Unit, onCamera: () -> Unit) {
    FullScreenUi(onShowFilePicker)
}

