package com.niksah.gagarin.screens.main

import com.darkrockstudios.libraries.mpfilepicker.MPFile
import com.niksah.gagarin.data.models.fold
import com.niksah.gagarin.data.repositories.ApiRepository
import com.niksah.gagarin.data.repositories.FileRepository
import com.niksah.gagarin.domain.FileRepositoryImpl
import com.niksah.gagarin.utils.base.BaseViewModel
import com.niksah.seconHack.data.models.Operation

class MainViewModel(
    private val apiRepository: ApiRepository,
    private  val fileRepository: FileRepository
) : BaseViewModel<MainState, MainEvent>(init()) {
    fun loadFile(file: MPFile<Any>) {
        launchViewModelScope {
            updateState {
                it.copy(operation = Operation.Preparing)
            }
            try {
                apiRepository.uploadImage(
                    file = file.getFileByteArray(),
                    type = file.path.split(".").last()
                ).fold(
                    ifLeft = {
                        updateState {
                            it.copy(operation = Operation.Failure(Unit))
                        }
                        print(it)
                        print("AAAAAAA")
                        trySendEvent(MainEvent.Failure("Network error"))
                    },
                    ifRight = {
                        print("AAAAAAA")
                        updateState {
                            it.copy(operation = Operation.Success(Unit))
                        }
                        fileRepository.file.emit(FileRepositoryImpl.File(it))
                        trySendEvent(MainEvent.Loaded)
                    }
                )
            } catch (error: Exception){
                updateState {
                    it.copy(operation = Operation.Failure(Unit))
                }
            }
        }
    }
}