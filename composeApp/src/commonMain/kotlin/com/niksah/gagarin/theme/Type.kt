package com.niksah.seconHack.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
	bodyMedium = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.Normal,
		fontSize = 16.sp
	),
	titleLarge = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight(700),
		fontSize = 24.sp
	),
	titleMedium = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight(500),
		fontSize = 24.sp
	),
	bodySmall = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.Normal,
		fontSize = 12.sp
	),
	/* Other default text styles to override
	button = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.W500,
		fontSize = 14.sp
	),
	caption = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.Normal,
		fontSize = 12.sp
	)
	*/
)