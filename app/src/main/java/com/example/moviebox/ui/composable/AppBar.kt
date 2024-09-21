package com.example.moviebox.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviebox.ui.theme.MBTheme

@Composable
fun MovieBoxAppBar(title: String = "Movie Box") {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MBTheme.colors.primary)
            .statusBarsPadding()
            .padding(horizontal = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            maxLines = 1,
            style = TextStyle(fontWeight = FontWeight.SemiBold),
            color = MBTheme.colors.white,
            modifier = Modifier.weight(1f)
                .padding(bottom = 10.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun AppBarPreview() {
    MovieBoxAppBar("Movie Box")
}