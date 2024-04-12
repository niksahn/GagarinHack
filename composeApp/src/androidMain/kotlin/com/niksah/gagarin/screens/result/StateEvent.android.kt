package com.niksah.gagarin.screens.result

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@OptIn(ExperimentalEncodingApi::class)
actual fun decode(byteArray: ByteArray): ImageBitmap {
    val encodedImageData = Base64.Default.decode(byteArray)
    return BitmapFactory.decodeByteArray(encodedImageData, 0, encodedImageData.size).asImageBitmap()
}
