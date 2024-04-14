package com.niksah.gagarin.screens.history

import androidx.compose.ui.graphics.ImageBitmap
import com.niksah.gagarin.screens.result.toStr
import com.niksah.gagarin.utils.base.Event
import com.niksah.gagarin.utils.base.State
import gagarinhak.composeapp.generated.resources.Res
import gagarinhak.composeapp.generated.resources.in_progress
import gagarinhak.composeapp.generated.resources.rejected
import gagarinhak.composeapp.generated.resources.sucsess
import org.jetbrains.compose.resources.DrawableResource

data class HistoryState(
    val history: List<History>,
    val isPrepairing: Boolean,
) : State()

fun init() = HistoryState(
    emptyList(), true
)

sealed class HistoryEvent : Event() {
    data class Failure(
        val message: String
    ) : HistoryEvent()

    data object OnItem : HistoryEvent()
}

data class History(
    val image: ImageBitmap?,
    val name: String,
    val data: String?,
    val status: Status,
    val id: String,
) {
    enum class Status {
        IN_PROGRESS, CHECKED, REJECTED;

        fun toDrawable(): DrawableResource = when (this) {
            IN_PROGRESS -> Res.drawable.in_progress
            CHECKED -> Res.drawable.sucsess
            REJECTED -> Res.drawable.rejected
        }
    }
}

suspend fun com.niksah.gagarin.data.models.History.toHistoryState() = History(
    image = null,
    data = series,
    status = when (status) {
        0 -> History.Status.IN_PROGRESS
        1 -> History.Status.CHECKED
        else -> History.Status.REJECTED
    },
    id = id ?: "",
    name = type.toStr(),
    //  date =
)
