package com.niksah.gagarin.screens.main

import com.niksah.gagarin.utils.base.State
import com.niksah.gagarin.utils.base.Event
import com.niksah.seconHack.data.models.Operation

data class MainState(
    val operation: Operation<Unit, Unit>?,
): State()

fun init() = MainState(
    operation = null
)


sealed class MainEvent : Event(){
    data class Failure(val message: String) : MainEvent()
    data object Loaded : MainEvent()
}