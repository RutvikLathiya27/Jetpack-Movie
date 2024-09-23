package com.example.moviebox.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.moviebox.ui.theme.MBTheme

@Composable
fun CircularIndeterminateProgressBar(isDisplayed: Boolean, verticalBias: Float) {
    if (isDisplayed) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()//.background(Color.Black.copy(alpha = 0.5f)),
        ) {
            val (progressBar) = createRefs()
            val topBias = createGuidelineFromTop(verticalBias)
            CircularProgressIndicator(
                modifier = Modifier.constrainAs(progressBar)
                {
                    top.linkTo(topBias)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                },
                color = MBTheme.colors.lightBlue
            )
        }

    }
}

@Composable
fun CircularProgressBar(modifier: Modifier = Modifier, color: Color = Color.Gray) {
    Box(modifier = modifier) {
        CircularProgressIndicator(color = color)
    }
}