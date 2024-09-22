package com.example.moviebox.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class MBTypography(
    val s1: TextStyle,
    val s2: TextStyle,
    val s3: TextStyle,
    val m1: TextStyle,
    val m2: TextStyle,
    val m3: TextStyle,
    val l1: TextStyle,
    val l2: TextStyle,
    val l3: TextStyle
)

val LocalMBTypography = staticCompositionLocalOf {
    MBTypography(
        s1 = TextStyle.Default,
        s2 = TextStyle.Default,
        s3 = TextStyle.Default,
        m1 = TextStyle.Default,
        m2 = TextStyle.Default,
        m3 = TextStyle.Default,
        l1 = TextStyle.Default,
        l2 = TextStyle.Default,
        l3 = TextStyle.Default
    )
}

val mbTypography = MBTypography(
    s1 = TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = 12.sp
    ),
    s2 = TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = 11.sp
    ),
    s3 = TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = 10.sp
    ),
    m1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    m2 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    m3 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    l1 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    ),
    l2 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    ),
    l3 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    )
)