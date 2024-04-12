package com.niksah.gagarin.screens.result

import androidx.compose.ui.graphics.ImageBitmap
import com.niksah.gagarin.utils.base.Event
import com.niksah.gagarin.utils.base.State
import kotlin.io.encoding.ExperimentalEncodingApi

sealed class ResultState : State() {
    data object Loading : ResultState()
    data class Content(
        val image: ImageBitmap,
        val fields: List<Field>
    ) : ResultState() {
        data class Field(
            val title: String,
            val value: String
        )
    }
}

sealed class ResultEvent : Event() {
    data object GoBack : ResultEvent()
}

expect fun decode(byteArray: ByteArray): ImageBitmap