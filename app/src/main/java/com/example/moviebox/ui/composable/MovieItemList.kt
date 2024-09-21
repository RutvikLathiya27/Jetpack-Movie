package com.example.moviebox.ui.composable

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.moviebox.data.models.MovieItem
import com.example.moviebox.data.remote.ApiURL
import com.example.moviebox.ui.theme.MBTheme
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MovieItemList(
    modifier: Modifier,
    dataStream: LazyPagingItems<MovieItem>,
    onClick: (Int) -> Unit
) {

    val isDisplayProgress = remember { mutableStateOf(true) }

    Column(modifier = Modifier.fillMaxSize()) {
        CircularIndeterminateProgressBar(isDisplayed = isDisplayProgress.value, 0.5f)
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = modifier.padding(vertical = 4.dp, horizontal = 6.dp)
        ) {

            items(dataStream.itemCount) { itemCount ->
                MovieItemPreview(
                    movieItem = dataStream[itemCount]!!, onClick
                )
            }
        }
    }

    dataStream.apply {
        Log.e("Tag", "data strea, >>>>>>>>>>>> $loadState")
        when {
            loadState.refresh is LoadState.Loading -> {
                isDisplayProgress.value = true
            }

            loadState.append is LoadState.Loading -> {
                isDisplayProgress.value = true
            }

            loadState.refresh is LoadState.NotLoading -> {
                isDisplayProgress.value = false
            }

            loadState.append is LoadState.NotLoading -> {
                isDisplayProgress.value = false
            }
        }
    }


}

@Composable
fun MovieItemPreview(movieItem: MovieItem, onClick: (Int) -> Unit) {

    Log.e("TAG", "id -----------------> ${movieItem.id} : ${movieItem.title}")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick(movieItem.id) }
            .padding(all = 4.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        GlideImage(
            imageModel = ApiURL.IMAGE_URL.plus(movieItem.posterPath),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            loading = {
                CircularProgressIndicator(color = Color.Gray)
            },
            failure = {

                Icon(imageVector = Icons.Filled.Warning, contentDescription = "Error")

            }
        )
        Box(
            modifier = Modifier
                .padding(all = 4.dp)
                .align(Alignment.BottomEnd)
                .clip(CircleShape)
                .background(color = MBTheme.colors.black80),
        ) {

            Text(
                text = String.format("%.1f", movieItem.voteAverage),
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MBTheme.colors.lightBlue,
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 6.dp)
            )

        }
    }

}