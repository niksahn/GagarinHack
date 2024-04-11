package com.niksah.gagarin

import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import com.niksah.gagarin.screens.camera.Camera
import com.niksah.gagarin.screens.main.Main
import com.niksah.seconHack.ui.theme.GagarinTheme
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition

@Composable
internal fun App() = PreComposeApp {
    val navigator = rememberNavigator()
    GagarinTheme {
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

                    }
                )
            }
        }
    }
}

internal expect fun openUrl(url: String?)