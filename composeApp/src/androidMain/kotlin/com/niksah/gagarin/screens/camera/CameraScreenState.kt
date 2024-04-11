package com.niksah.gagarin.screens.camera

import com.niksah.gagarin.utils.base.State

data class CameraScreenState(
	val isVideoRecording: Boolean,
	val makingPhoto: Boolean,
	val camera: CamState,
	val uploadedFileId: String?
) : State()

enum class CamState {
	VIDEO, PHOTO
}

internal fun init() = CameraScreenState(
	isVideoRecording = false,
	makingPhoto = false,
	camera = CamState.VIDEO,
	uploadedFileId = null
)