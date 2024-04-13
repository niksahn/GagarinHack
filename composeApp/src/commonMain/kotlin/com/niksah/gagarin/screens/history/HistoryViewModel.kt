package com.niksah.gagarin.screens.history

import com.niksah.gagarin.data.models.fold
import com.niksah.gagarin.data.repositories.ApiRepository
import com.niksah.gagarin.data.repositories.ResponseRepository
import com.niksah.gagarin.data.repositories.SettingsRepository
import com.niksah.gagarin.screens.result.decode
import com.niksah.gagarin.screens.result.encodeToByteArray
import com.niksah.gagarin.utils.base.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class HistoryViewModel(
    private val apiRepository: ApiRepository,
    private val settingsRepository: SettingsRepository,
    private val responseRepository: ResponseRepository
) : BaseViewModel<HistoryState, HistoryEvent>(init()) {

    init {
        fetch()
    }

    fun onItem(id: String) {
        launchViewModelScope {
            val selected = responseRepository.history.value.first { it.id == id }
            responseRepository.selected.emit(selected)
        }
    }

    fun fetch() {
        val mutex = Mutex()
        launchViewModelScope {
            settingsRepository.id.get()
                ?.let { apiRepository.getHistory(it) }
                ?.fold(
                    ifLeft = { message ->
                        trySendEvent(HistoryEvent.Failure("Ошибка интернет соединения"))
                    },
                    ifRight = { history ->
                        responseRepository.history.emit(history)
                        val historyStr = history.map { it.toHistoryState() }
                        updateState {
                            it.copy(
                                history = historyStr,
                                isPrepairing = false
                            )
                        }
                        mutex.withLock {
                            history.forEach { curhist ->
                                async {
                                    apiRepository.getImage(curhist.fileId)
                                        .fold(
                                            ifRight = { image ->
                                                println("AAAAA $image")
                                                updateState {
                                                    it.copy(
                                                        history = it.history.map { hisrItem ->
                                                            if (hisrItem.id == curhist.id) {
                                                                hisrItem.copy(
                                                                    image = decode(encodeToByteArray(image))
                                                                )
                                                            } else {
                                                                hisrItem
                                                            }
                                                        }
                                                    )
                                                }
                                            },
                                            ifLeft = {}
                                        )
                                }
                            }
                        }
                    }
                )
        }
    }
}
