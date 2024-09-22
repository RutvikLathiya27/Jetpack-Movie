package com.example.moviebox.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val Black80 = Color(0xCC000000)
private val White = Color(0xffffffff)
private val Primary = Color(0xff000000)
private val Secondary = Color(0xff1C1C1E)
private val SystemGrey = Color(0xFF3A3A3C)
private val LightBlue = Color(0xff6EACDE)
private val LightBlue75 = Color(0xBF6EACDE)
private val White50 = Color(0x80FFFFFF)

@Immutable
data class MBColors(
    val black80: Color,
    val white: Color,
    val primary: Color,
    val secondary: Color,
    val systemGrey: Color,
    val lightBlue: Color,
    val lightBlue75: Color,
    val white50 : Color
)

val LocalMBColors = staticCompositionLocalOf {
    MBColors(
        black80 = Color.Unspecified,
        white = Color.Unspecified,
        primary = Color.Unspecified,
        secondary = Color.Unspecified,
        systemGrey = Color.Unspecified,
        lightBlue = Color.Unspecified,
        lightBlue75 = Color.Unspecified,
        white50 = Color.Unspecified
    )
}

val mbColors = MBColors(
    black80 = Black80,
    white = White,
    primary = Primary,
    secondary = Secondary,
    systemGrey = SystemGrey,
    lightBlue = LightBlue,
    lightBlue75 = LightBlue75,
    white50 = White50
)