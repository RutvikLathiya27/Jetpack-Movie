package com.example.moviebox.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun MovieBoxTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalMBColors provides mbColors,
        LocalMBTypography provides mbTypography,
        content = content
    )
}

object MBTheme {
    val colors: MBColors
        @Composable
        get() = LocalMBColors.current

    val typography: MBTypography
        @Composable
        get() = LocalMBTypography.current
}