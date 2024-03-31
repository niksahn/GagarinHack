package com.niksah.gagarin

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import gagarinhak.composeapp.generated.resources.*
import com.niksah.gagarin.theme.AppTheme
import com.niksah.gagarin.theme.LocalThemeIsDark
import com.niksah.gagarin.ui.main.Main
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import com.niksah.gagarin.utils.views.Spacer
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator

@Composable
internal fun App() = AppTheme {
    PreComposeApp {
        val navigator = rememberNavigator()
        MaterialTheme {
            NavHost(
                navigator = navigator,
                initialRoute = "/home",
            ) {
                scene("/home") {
                    Main()
                }
            }

        }
    }
}


internal expect fun openUrl(url: String?)