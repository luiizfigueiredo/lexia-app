package com.lexia.app.shared.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme =
    lightColorScheme(
        primary = BlueWord,
        secondary = GreenWord,
        tertiary = PinkWord,
        background = SoftBlueBackground,
        surface = WhiteSurface,
        onBackground = DarkText,
        onSurface = DarkText,
    )

@Composable
fun LexiaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content,
    )
}
