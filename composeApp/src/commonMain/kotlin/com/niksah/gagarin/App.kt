package com.niksah.gagarin

import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.niksah.gagarin.data.CurrentPlatform
import com.niksah.gagarin.data.Platform
import com.niksah.gagarin.screens.camera.Camera
import com.niksah.gagarin.screens.history.HistoryUI
import com.niksah.gagarin.screens.main.Main
import com.niksah.gagarin.utils.views.bottomBar.BottomBar
import com.niksah.gagarin.utils.views.bottomBar.BottomBarDestination
import com.niksah.seconHack.ui.theme.GagarinTheme
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition

@Composable
internal fun App() = PreComposeApp {
    val navigator = rememberNavigator()
    val currentDestination = navigator.currentEntry.collectAsStateWithLifecycle(null)
    GagarinTheme {
        Scaffold(
            bottomBar = {
                if (BottomBarDestination.entries.any {
                        it.direction == currentDestination.value?.route?.route
                    } && CurrentPlatform.current == Platform.Android
                ) {
                    BottomBar(navigator)
                }
            }
        ) {
            NavHost(
                navigator = navigator,
                initialRoute = "/home",
                navTransition = NavTransition(),
            ) {
                scene(
                    "/home",
                ) {
                    Main(
                        onCamera = { navigator.navigate("/camera") },
                        onLoaded = {}
                    )
                }
                scene(
                    "/camera",
                    navTransition = NavTransition(
                        createTransition = slideInVertically(initialOffsetY = { it }),
                        destroyTransition = slideOutVertically(targetOffsetY = { it }),
                        pauseTransition = scaleOut(targetScale = 0.9f),
                        resumeTransition = scaleIn(initialScale = 0.9f),
                        exitTargetContentZIndex = 1f,
                    ),
                ) {
                    Camera(
                        makedPhoto = {

                        },
                        goBack = {
                            navigator.goBack()
                        }
                    )
                }
                scene(
                    "/history",
                ) {
                    HistoryUI()
                }
            }
        }
    }
}
