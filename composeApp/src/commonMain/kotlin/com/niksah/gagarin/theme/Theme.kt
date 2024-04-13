package com.niksah.seconHack.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColorScheme(
	primary = Purple200,
	onPrimary = Purple500,
	secondary = Purple700,
	background = Color.White,
	surface = Color.Black,
	onSurface = link
)

@Composable
fun GagarinTheme(
	content: @Composable () -> Unit
) {
	MaterialTheme(
		colorScheme = LightColorPalette,
		typography = Typography,
		shapes = Shapes,
		content = content
	)
}