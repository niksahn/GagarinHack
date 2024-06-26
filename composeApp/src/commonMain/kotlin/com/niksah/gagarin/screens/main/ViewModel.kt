package com.niksah.gagarin.screens.main

import com.darkrockstudios.libraries.mpfilepicker.MPFile
import com.niksah.gagarin.data.models.fold
import com.niksah.gagarin.data.repositories.ApiRepository
import com.niksah.gagarin.data.repositories.ResponseRepository
import com.niksah.gagarin.data.repositories.SettingsRepository
import com.niksah.gagarin.utils.base.BaseViewModel
import com.niksah.seconHack.data.models.Operation

class MainViewModel(
    private val apiRepository: ApiRepository,
    private val settingsRepository: SettingsRepository
) : BaseViewModel<MainState, MainEvent>(init()) {

    init {
        if (!settingsRepository.id.exists()) {
            settingsRepository.id.set(
                // "123"
                ('A'..'z').map { it }
                    .shuffled()
                    .subList(0, 16)
                    .joinToString("")
                    .replace("/", "")
                    .replace("]", "")
                    .replace("\\", "")
            )
        }
    }

    fun loadFile(file: MPFile<Any>) {
        updateState {
            it.copy(operation = Operation.Preparing)
        }
        launchViewModelScope {
            try {
                apiRepository.uploadImage(
                    file = file.getFileByteArray(),
                    type = settingsRepository.id.get() ?: ""
                ).fold(
                    ifLeft = {
                        trySendEvent(MainEvent.Failure("Ошибка интернет соединения"))
                        updateState {
                            it.copy(operation = Operation.Failure(Unit))
                        }
                        print(it)
                        print("AAAAAAA")
                    },
                    ifRight = {
                        print("AAAAAAA")
                        updateState {
                            it.copy(operation = Operation.Success(Unit))
                        }
                        trySendEvent(MainEvent.Loaded)
                    }
                )
            } catch (e: Exception) {
                e.printStackTrace()
                updateState {
                    trySendEvent(MainEvent.Failure("Неизвестная ошибка"))
                    it.copy(operation = Operation.Failure(Unit))
                }
            }
        }
    }
}

