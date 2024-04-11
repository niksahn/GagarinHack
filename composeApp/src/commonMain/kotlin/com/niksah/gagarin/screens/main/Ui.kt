package com.niksah.gagarin.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.darkrockstudios.libraries.mpfilepicker.MPFile
import com.niksah.gagarin.utils.base.subscribeEvents
import com.niksah.gagarin.utils.base.subscribeScreenState
import com.niksah.gagarin.utils.views.Loader
import com.niksah.seconHack.data.models.Operation
import moe.tlaster.precompose.koin.koinViewModel

@Composable
internal fun Main(
    onCamera: () -> Unit,
    onLoaded: () -> Unit
) {
    val viewModel = koinViewModel(MainViewModel::class)
    var showFilePicker by remember { mutableStateOf(false) }
    val fileType = listOf("jpg", "png")
    val state = viewModel.subscribeScreenState()
    FileChooser(
        showFilePicker = showFilePicker,
        fileType = fileType,
        close = { showFilePicker = false },
        loadFile = {
            if (it != null) {
                viewModel.loadFile(it)
            }
        }
    )

    viewModel.subscribeEvents {
        when (it) {
            is MainEvent.Failure -> {}
            MainEvent.Loaded -> onLoaded()
        }
    }
    Content(
        onShowFilePicker = { showFilePicker = true },
        onCamera = onCamera
    )
    if (state.value.operation is Operation.Preparing) {
        Loader()
    }
}

@Composable
expect fun FileChooser(
    showFilePicker: Boolean,
    fileType: List<String>,
    close: () -> Unit,
    loadFile: (MPFile<Any>?) -> Unit
)

@Composable
expect fun Content(
    onShowFilePicker: () -> Unit,
    onCamera: () -> Unit,
)

//{
//
//    Scaffold {
//        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//            Column(
//                modifier = Modifier.padding(24.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Spacer(size = 24.dp)
//                if (CurrentPlatform.current == Platform.Android) {
//                    CameraBox(onCamera)
//                }
//                Spacer(size = 24.dp)
//                Box(
//                    modifier = Modifier
//                        .size(loaderSize.first, loaderSize.second)
//                        .clip(MaterialTheme.shapes.extraLarge)
//                        .clickable(onClick = onShowFilePicker)
//                        .background(MaterialTheme.colorScheme.onPrimary),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Column(
//                        verticalArrangement = Arrangement.spacedBy(16.dp),
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Image(
//                            painter = painterResource(Res.drawable.file_upload),
//                            contentDescription = null,
//                            modifier = Modifier.size(loaderIconSize.first, loaderIconSize.second)
//                        )
//                        Text(
//                            text = stringResource(Res.string.load_file),
//                            color = MaterialTheme.colorScheme.secondary,
//                            softWrap = false,
//                            textAlign = TextAlign.Center,
//                            style = MaterialTheme.typography.titleLarge,
//                        )
//                    }
//                }
//                Spacer(size = 32.dp)
//            }
//        }
//    }
//}
//
//@Composable
//fun CameraBox(
//    onCamera: () -> Unit
//) {
//    Box(
//        modifier = Modifier
//            .size(368.dp, 304.dp)
//            .clip(MaterialTheme.shapes.extraLarge)
//            .background(MaterialTheme.colorScheme.onPrimary)
//            .clickable(onClick = onCamera),
//        contentAlignment = Alignment.Center
//    ) {
//        Column(
//            verticalArrangement = Arrangement.spacedBy(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Image(
//                painter = painterResource(Res.drawable.camera),
//                contentDescription = null,
//                modifier = Modifier.size(144.dp, 120.dp)
//            )
//            Text(
//                text = stringResource(Res.string.fix_violation),
//                color = MaterialTheme.colorScheme.secondary,
//                softWrap = false,
//                textAlign = TextAlign.Center,
//                style = MaterialTheme.typography.titleLarge,
//            )
//        }
//    }
//}