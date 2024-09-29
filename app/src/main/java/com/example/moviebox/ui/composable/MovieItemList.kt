package com.example.moviebox.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviebox.R
import com.example.moviebox.data.models.MovieItem
import com.example.moviebox.data.remote.ApiURL
import com.example.moviebox.ui.theme.MBTheme

@Composable
fun MovieItemList(
    modifier: Modifier,
    dataStream: LazyPagingItems<MovieItem>,
    onClick: (Int) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (dataStream.loadState.refresh == LoadState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = modifier.padding(vertical = 4.dp, horizontal = 6.dp)
        ) {
            if (dataStream.loadState.prepend is LoadState.Loading) {
                item {
                    CircularProgressBar()
                }
            }
            items(dataStream.itemCount) { itemCount ->
                MovieItemPreview(
                    movieItem = dataStream[itemCount],
                    onClick = onClick
                )
            }
            if (dataStream.loadState.append is LoadState.Loading) {
                item {
                    CircularProgressBar()
                }
            }
        }
    }
}

@Composable
fun CircularProgressBar(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun MovieItemPreview(movieItem: MovieItem?, onClick: (Int) -> Unit) {
    movieItem ?: return

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable { onClick(movieItem.id) }
            .padding(all = 4.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(ApiURL.IMAGE_URL.plus(movieItem.posterPath))
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            placeholder = painterResource(R.drawable.ic_placeholder),
            contentDescription = null,
            fallback = painterResource(R.drawable.ic_placeholder),
        )
        Text(
            text = String.format("%.1f", movieItem.voteAverage),
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MBTheme.colors.lightBlue,
            modifier = Modifier
                .padding(all = 4.dp)
                .align(Alignment.BottomEnd)
                .clip(CircleShape)
                .background(color = MBTheme.colors.black80)
                .padding(vertical = 4.dp, horizontal = 6.dp)
        )
    }
}