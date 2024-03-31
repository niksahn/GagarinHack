package com.niksah.gagarin.ui.main

import com.niksah.gagarin.utils.base.State
import com.niksah.gagarin.utils.base.Event
import com.niksah.seconHack.data.models.Operation

data class MainState(
    val operation: Operation<Unit, Unit>,
): State()

fun init() = MainState(
    operation = Operation.Preparing
)


sealed class MainEvent : Event(){

}