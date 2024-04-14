package com.niksah.gagarin.screens.result

import androidx.compose.ui.graphics.ImageBitmap
import com.niksah.gagarin.data.models.History
import com.niksah.gagarin.data.models.toStr
import com.niksah.gagarin.utils.base.Event
import com.niksah.gagarin.utils.base.State
import gagarinhak.composeapp.generated.resources.Res
import gagarinhak.composeapp.generated.resources.doc_type
import gagarinhak.composeapp.generated.resources.number
import gagarinhak.composeapp.generated.resources.page_num
import gagarinhak.composeapp.generated.resources.passport
import gagarinhak.composeapp.generated.resources.pts
import gagarinhak.composeapp.generated.resources.rider_r
import gagarinhak.composeapp.generated.resources.series
import gagarinhak.composeapp.generated.resources.sts
import org.jetbrains.compose.resources.getString
import kotlin.io.encoding.ExperimentalEncodingApi

sealed class ResultState : State() {
    data object Loading : ResultState()
    data class Content(
        val image: ImageBitmap?,
        val fields: List<Field>
    ) : ResultState() {
        data class Field(
            val title: String,
            val value: String
        )
    }
}

suspend fun History.toResultState(image: ImageBitmap?): ResultState.Content {
    val strings = mapOf(
        getString(Res.string.doc_type) to type.toStr(),
        getString(Res.string.page_num) to page_number.toString(),
        getString(Res.string.series) to series.toString(),
        getString(Res.string.number) to number.toString()
    ).map {
        ResultState.Content.Field(title = it.key, value = it.value)
    }
    return ResultState.Content(
        image = image,
        fields = strings.plus(
            data?.map {
                ResultState.Content.Field(title = it.key, value = it.value)
            } ?: emptyList()
        )
    )
}

suspend fun History.DocumentType?.toStr() =
    when (this) {
        History.DocumentType.PERSONAL_PASSPORT -> getString(Res.string.passport)
        null -> "Неизвестный тип"
        History.DocumentType.VEHICLE_PASSPORT -> getString(Res.string.pts)
        History.DocumentType.DRIVER_LICENSE -> getString(Res.string.rider_r)
        History.DocumentType.VEHICLE_CERTIFICATE -> getString(Res.string.sts)
    }

sealed class ResultEvent : Event() {
    data object GoBack : ResultEvent()
    data class Failure(val message: String) : ResultEvent()
}

expect fun encodeToByteArray(image: String): ByteArray

expect fun decode(byteArray: ByteArray): ImageBitmap