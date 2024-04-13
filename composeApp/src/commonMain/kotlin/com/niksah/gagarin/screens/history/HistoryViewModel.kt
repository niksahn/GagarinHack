package com.niksah.gagarin.screens.history

import com.niksah.gagarin.data.models.fold
import com.niksah.gagarin.data.repositories.ApiRepository
import com.niksah.gagarin.data.repositories.SettingsRepository
import com.niksah.gagarin.utils.base.BaseViewModel

class HistoryViewModel(
    private val apiRepository: ApiRepository,
    private val settingsRepository: SettingsRepository
) : BaseViewModel<HistoryState, HistoryEvent>(HistoryState.Preparing) {

    init {
        fetch()
    }

    fun fetch() {
        launchViewModelScope {
            settingsRepository.id.get()
                ?.let { apiRepository.getHistory(it) }
                ?.fold(
                    ifLeft = { message ->
                        trySendEvent(HistoryEvent.Failure("Ошибка интернет соединения"))
                    },
                    ifRight = {
                        updateState {
                            HistoryState.Content(
                                history = listOf() // TODO
                            )
                        }
                    }
                )
        }
    }
}