package com.niksah.gagarin.screens.history

import androidx.compose.ui.graphics.ImageBitmap
import com.niksah.gagarin.utils.base.Event
import com.niksah.gagarin.utils.base.State

sealed class HistoryState : State() {
    data object Preparing : HistoryState()
    data class Content(
        val history: List<History>
    ) : HistoryState()
}

sealed class HistoryEvent : Event()
data class History(
    val image: ImageBitmap,
    val name: String,
    val date: String,
    val data: String,
    val status: Status
) {
    enum class Status {
        IN_PROGRESS, CHECKED, REJECTED
    }
}
