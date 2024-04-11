package com.niksah.gagarin.screens.camera

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.niksah.gagarin.screens.main.MainViewModel
import com.niksah.gagarin.utils.base.subscribeEvents
import com.niksah.gagarin.utils.base.subscribeScreenState
import com.niksah.gagarin.views.MissingPermissionsComponent
import com.ujizin.camposer.CameraPreview
import com.ujizin.camposer.state.CamSelector
import com.ujizin.camposer.state.CameraState
import com.ujizin.camposer.state.rememberCamSelector
import com.ujizin.camposer.state.rememberCameraState
import moe.tlaster.precompose.koin.koinViewModel

@Composable
internal actual fun Camera(
    makedPhoto:() -> Unit
) {

    val context = LocalContext.current
    val viewModel = koinViewModel(CameraViewModel::class)
    val state by viewModel.subscribeScreenState()

    MissingPermissionsComponent {
        val cameraState = rememberCameraState()
        val camSelector by rememberCamSelector(CamSelector.Back)
        viewModel.subscribeEvents {
            when (it) {
                is CameraEvent.Failure -> Toast.makeText(
                    context,
                    "Ошибка загрузки файла ${it.message}",
                    Toast.LENGTH_SHORT
                ).show()

                CameraEvent.MakedPhoto -> {
                    Toast.makeText(
                        context,
                        "Фото сделано",
                        Toast.LENGTH_SHORT
                    ).show()
                    viewModel.getFile()?.let {
                        makedPhoto()
                    }
                }
            }
        }
        CamUi(
            cameraState = cameraState,
            camSelector = camSelector,
            makingPhoto = state.makingPhoto,
            takePicture = {
                when (state.camera) {
                    CamState.PHOTO -> viewModel.takePicture(cameraState)
                    CamState.VIDEO -> viewModel.takePicture(cameraState) //viewModel.makeVideo(cameraState)
                }
            }
        )
    }
}

@Composable
fun CamUi(
    cameraState: CameraState,
    makingPhoto: Boolean,
    camSelector: CamSelector,
    takePicture: () -> Unit,
) {

    CameraPreview(
        cameraState = cameraState,
        camSelector = camSelector,
    ) {
        if (makingPhoto) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
            )
        } else {
            Column {
                Box(modifier = Modifier.weight(1F))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = takePicture,
                        color = Color.White,
                        size = 84.dp
                    )
                }
            }
        }
    }
}

@Composable
fun Button(
    onClick: () -> Unit,
    color: Color,
    size: Dp,
    enabled: Boolean = true,
    contentPaddingValues: PaddingValues = PaddingValues(0.dp),
    content: @Composable BoxScope.() -> Unit = {},
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(if (pressed) 0.85F else 1F, label = "")

    Box(
        modifier = Modifier
            .scale(scale)
            .clip(CircleShape)
            .clickable(
                enabled = enabled,
                indication = rememberRipple(bounded = true),
                interactionSource = interactionSource,
                onClick = onClick,
            )
            .size(size)
            .background(color)
            .padding(contentPaddingValues),
        contentAlignment = Alignment.Center,
        content = content
    )
}