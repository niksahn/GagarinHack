package com.niksah.gagarin.screens.camera

import android.content.Context
import android.net.Uri
import android.os.Build
import android.util.Log
import com.niksah.gagarin.data.FileStorageRepository
import com.niksah.gagarin.data.models.fold
import com.niksah.gagarin.data.repositories.ApiRepository
import com.niksah.gagarin.data.repositories.FileRepository
import com.niksah.gagarin.domain.FileRepositoryImpl
import com.niksah.gagarin.utils.base.BaseViewModel
import com.ujizin.camposer.state.CameraState
import com.ujizin.camposer.state.ImageCaptureResult
import java.io.IOException

class CameraViewModel(
    private val fileRepository: FileRepository,
    private val storageRepository: FileStorageRepository,
    private val apiRepository: ApiRepository
) : BaseViewModel<CameraScreenState, CameraEvent>(init()) {
    fun takePicture(cameraState: CameraState) {
        launchViewModelScope {
            updateState {
                it.copy(makingPhoto = true)
            }
        }
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> cameraState.takePicture(
                storageRepository.imageContentValues,
                onResult = ::onImageResult
            )

            else -> cameraState.takePicture(
                storageRepository.getFile("jpg"),
                ::onImageResult
            )
        }
    }

    private fun onImageResult(imageResult: ImageCaptureResult) {
        launchViewModelScope {
            updateState { it.copy(makingPhoto = false) }
            when (imageResult) {
                is ImageCaptureResult.Error -> trySendEvent(CameraEvent.Failure(imageResult.throwable.message))
                is ImageCaptureResult.Success -> {
                    launchViewModelScope {
                        getFile()?.readBytes()?.let { file ->
                            apiRepository.uploadImage(file, "jpg").fold(
                                ifLeft = {
                                    print(it)
                                    trySendEvent(CameraEvent.Failure("Network error"))
                                },
                                ifRight = {
                                    fileRepository.file.emit(it)
                                    trySendEvent(CameraEvent.MakedPhoto)
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    @Throws(IOException::class)
    private fun readBytes(context: Context, uri: Uri): ByteArray? =
        context.contentResolver.openInputStream(uri)?.use { it.buffered().readBytes() }

    fun onResultScan(uri: Uri, context: Context) {
        launchViewModelScope {
            readBytes(context, uri)?.let { file ->
                apiRepository.uploadImage(file, "jpg").fold(
                    ifLeft = {
                        print(it)
                        trySendEvent(CameraEvent.Failure("Network error"))
                    },
                    ifRight = {
                        fileRepository.file.emit(it)
                        trySendEvent(CameraEvent.MakedPhoto)
                    }
                )
            }
        }
    }

    fun onNotEnableScanner() {
        updateState {
            it.copy(showScanner = false)
        }
    }

    fun getFile() = storageRepository.lastFile
}
