package com.niksah.gagarin.screens.result

import com.niksah.gagarin.data.network.Response
import com.niksah.gagarin.data.repositories.FileRepository
import com.niksah.gagarin.utils.base.BaseViewModel

class ResultViewModel(
    val fileRepositoryImpl: FileRepository
) : BaseViewModel<ResultState, ResultEvent>(ResultState.Loading) {

    init {
        launchViewModelScope {
            fileRepositoryImpl.file.value?.let { response ->
                updateState {
                    response.toState()
                }
            }
        }
    }

    fun onBack() {
        trySendEvent(ResultEvent.GoBack)
    }
}

fun Response.toState() = ResultState.Content(
    image = decode(file),
    fields = fields.map { ResultState.Content.Field(it.key, it.value) }
)