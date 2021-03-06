package com.chskela.monoapplication.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Branding,
    onPrimary = White,
    secondary = SecondaryDark,
    background = BackgroundDark,
    error = Red,
    onError = White,
    surface = BlackDark,
    onSurface = PrimaryDark,
    secondaryVariant = GrayDark
)

private val LightColorPalette = lightColors(
    primary = Branding,
    onPrimary = White,
    secondary = Secondary,
    background = Background,
    error = Red,
    onError = White,
    surface = White,
    onSurface = Primary,
    secondaryVariant = Gray,

)

@Composable
fun MonoApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = TypographyRoboto,
        shapes = Shapes,
        content = content
    )
}