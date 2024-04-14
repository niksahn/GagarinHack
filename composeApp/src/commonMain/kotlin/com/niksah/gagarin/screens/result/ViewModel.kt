package com.niksah.gagarin.screens.result

import androidx.compose.ui.graphics.ImageBitmap
import com.niksah.gagarin.data.models.fold
import com.niksah.gagarin.data.repositories.ApiRepository
import com.niksah.gagarin.data.repositories.ResponseRepository
import com.niksah.gagarin.utils.base.BaseViewModel

class ResultViewModel(
    private val responseRepository: ResponseRepository,
    private val apiRepository: ApiRepository
) : BaseViewModel<ResultState, ResultEvent>(ResultState.Loading) {

    init {
        responseRepository.selected.value?.let { resp ->
            launchViewModelScope {
                var image: ImageBitmap? = null
                resp.fileId?.let {
                    apiRepository.getImage(it).fold(
                        ifLeft = { },
                        ifRight = { im -> image = decode(encodeToByteArray(im)) }
                    )
                }
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

    fun save() {
        trySendEvent(ResultEvent.Failure("Ошибка получения файла"))

        responseRepository.selected.value?.let { resp ->
            launchViewModelScope {
                resp.id?.let {
                    apiRepository.getSva(it).fold(
                        ifLeft = {
                            trySendEvent(ResultEvent.Failure("Ошибка получения файла"))
                        },
                        ifRight = {

                        }
                    )
                }
            }
        }
    }
}

//expect fun saveToFile(
//    apiRepository: ApiRepository,
//)
