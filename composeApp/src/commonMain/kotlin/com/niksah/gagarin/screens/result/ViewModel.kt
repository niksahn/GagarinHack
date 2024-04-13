package com.niksah.gagarin.screens.result

import androidx.compose.ui.graphics.ImageBitmap
import com.niksah.gagarin.data.models.Response
import com.niksah.gagarin.data.models.fold
import com.niksah.gagarin.data.repositories.ApiRepository
import com.niksah.gagarin.data.repositories.ResponseRepository
import com.niksah.gagarin.utils.base.BaseViewModel

class ResultViewModel(
    responseRepository: ResponseRepository,
    private val apiRepository: ApiRepository
) : BaseViewModel<ResultState, ResultEvent>(ResultState.Loading) {

    init {
        responseRepository.selected.value?.let { resp ->
            launchViewModelScope {
                var image: ImageBitmap? = null
                apiRepository.getImage(resp.fileId).fold(
                    ifLeft = { },
                    ifRight = { im -> image = decode(encodeToByteArray(im)) }
                )
                val stat = resp.toResultState(image)
                updateState {
                    stat
                }
            }
        } ?: trySendEvent(ResultEvent.GoBack)
    }

    fun onBack() {
        trySendEvent(ResultEvent.GoBack)
    }
}
