package com.niksah.gagarin.screens.main

import android.content.Context
import com.darkrockstudios.libraries.mpfilepicker.MPFile

fun getFileBytes(file: MPFile<Any>, context: Context): ByteArray {
    val uri = file as AndroidFile
    val file = createTempFile()
    uri.let { context.contentResolver.openInputStream(it.platformFile) }.use { input ->
        file.outputStream().use { output ->
            input?.copyTo(output)
        }
    }
    return file.readBytes()
}
