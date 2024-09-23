package com.example.moviebox.ui.screens.movie_detail_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviebox.R
import com.example.moviebox.data.models.artist.Cast
import com.example.moviebox.data.remote.ApiURL
import com.example.moviebox.ui.composable.CircularProgressBar
import com.example.moviebox.ui.composable.MovieBoxAppBar
import com.example.moviebox.ui.theme.MBTheme

@Composable
fun MovieDetailScreen(movieId: Int, navController: NavHostController) {

    val movieDetailViewModel = hiltViewModel<MovieDetailViewModel>()
    val movieDetail by movieDetailViewModel.movieDetail.collectAsStateWithLifecycle()
    val isLoading by movieDetailViewModel.isLoading.collectAsStateWithLifecycle()
    val artist by movieDetailViewModel.getMovieCast(movieId).collectAsStateWithLifecycle()
    var isArtistVisible by remember { mutableStateOf(true) }
    val scrollState = rememberLazyListState()

    LaunchedEffect(key1 = Unit) {
        movieDetailViewModel.getMovieDetail(movieId)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MBTheme.colors.secondary),
        topBar = {
            MovieBoxAppBar(title = "Movie Details")
        }
    ) { innerPadding ->
        if (isLoading) {
            CircularProgressBar(Modifier.fillMaxSize())
        } else {
            ConstraintLayout(
                modifier = Modifier
                    .background(MBTheme.colors.secondary)
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                val (bgImage, mainImage, title, release, overview, timeIcon, timeText, starIcon, starText, divider, castTitle, arrow, casts) = createRefs()
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(ApiURL.IMAGE_URL.plus(movieDetail?.backdropPath))
                        .crossfade(true)
                        .build(),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxHeight(0.45f)
                        .fillMaxWidth()
                        .constrainAs(bgImage) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    placeholder = painterResource(R.drawable.ic_placeholder),
                    contentDescription = null,
                    fallback = painterResource(R.drawable.ic_placeholder),
                )
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(ApiURL.IMAGE_URL.plus(movieDetail?.posterPath))
                        .crossfade(true)
                        .build(),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .constrainAs(mainImage) {
                            top.linkTo(bgImage.bottom)
                            bottom.linkTo(bgImage.bottom)
                            end.linkTo(parent.end)
                        }
                        .padding(end = 8.dp)
                        .width(150.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .aspectRatio((1f / 1.33f)),
                    placeholder = painterResource(R.drawable.ic_placeholder),
                    contentDescription = null,
                    fallback = painterResource(R.drawable.ic_placeholder),
                )
                Text(
                    text = movieDetail?.title ?: "",
                    fontSize = 18.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                    color = MBTheme.colors.white,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp, start = 12.dp, end = 12.dp)
                        .basicMarquee()
                        .constrainAs(title) {
                            top.linkTo(bgImage.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(mainImage.start)
                            width = Dimension.fillToConstraints
                        }
                )
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_clock),
                    contentDescription = null,
                    modifier = Modifier
                        .size(16.dp)
                        .constrainAs(timeIcon) {
                            start.linkTo(parent.start, 12.dp)
                            top.linkTo(title.bottom, 6.dp)
                        }
                )
                Text(
                    text = movieDetail?.runtime.toString().plus(" min"),
                    fontSize = 14.sp,
                    color = MBTheme.colors.white,
                    modifier = Modifier.constrainAs(timeText) {
                        start.linkTo(timeIcon.end, 4.dp)
                        top.linkTo(timeIcon.top)
                        bottom.linkTo(timeIcon.bottom)
                    }
                )
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_rating),
                    contentDescription = null,
                    modifier = Modifier
                        .size(16.dp)
                        .constrainAs(starIcon) {
                            start.linkTo(timeText.end, 12.dp)
                            top.linkTo(timeIcon.top)
                            bottom.linkTo(timeIcon.bottom)
                        }
                )
                Text(
                    text = String.format("%.1f", movieDetail?.rating),
                    fontSize = 14.sp,
                    color = MBTheme.colors.white,
                    modifier = Modifier.constrainAs(starText) {
                        start.linkTo(starIcon.end, 4.dp)
                        top.linkTo(timeIcon.top)
                        bottom.linkTo(timeIcon.bottom)
                    }
                )
                Text(
                    text = "Release on : ${movieDetail?.releaseDate}",
                    color = MBTheme.colors.white,
                    modifier = Modifier
                        .constrainAs(release) {
                            start.linkTo(parent.start, 12.dp)
                            top.linkTo(timeIcon.bottom, 5.dp)
                        }
                )
                Text(
                    text = movieDetail?.overview ?: "",
                    color = Color.White,
                    modifier = Modifier
                        .constrainAs(overview) {
                            start.linkTo(parent.start, 12.dp)
                            end.linkTo(parent.end, 12.dp)
                            top.linkTo(mainImage.bottom, 16.dp)
                            width = Dimension.fillToConstraints
                        }
                )

                artist?.let { artist ->
                    HorizontalDivider(
                        modifier = Modifier.constrainAs(divider) {
                            start.linkTo(parent.start, 12.dp)
                            end.linkTo(parent.end, 12.dp)
                            top.linkTo(overview.bottom, 20.dp)
                            width = Dimension.fillToConstraints
                        }
                    )
                    Text(
                        text = "Casts",
                        color = MBTheme.colors.white,
                        modifier = Modifier.constrainAs(castTitle) {
                            start.linkTo(parent.start, 12.dp)
                            top.linkTo(arrow.top)
                            bottom.linkTo(arrow.bottom)
                        }
                    )
                    IconButton(
                        onClick = {
                            isArtistVisible = !isArtistVisible
                        },
                        content = {
                            Icon(
                                imageVector = if (isArtistVisible) {
                                    Icons.Outlined.KeyboardArrowUp
                                } else {
                                    Icons.Outlined.KeyboardArrowDown
                                },
                                contentDescription = null,
                                tint = MBTheme.colors.lightBlue
                            )
                        },
                        modifier = Modifier.constrainAs(arrow) {
                            top.linkTo(divider.bottom)
                            end.linkTo(parent.end)
                        }
                    )
                    if (isArtistVisible) {
                        LazyRow(
                            state = scrollState,
                            contentPadding = PaddingValues(8.dp),
                            modifier = Modifier.constrainAs(casts) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(castTitle.bottom, 12.dp)
                            }
                        ) {
                            items(artist.cast.size) { itemCount ->
                                val actor = artist.cast[itemCount]
                                ActorItem(actor)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ActorItem(actor: Cast) {
    Column(
        modifier = Modifier.padding(end = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(ApiURL.IMAGE_URL.plus(actor.profilePath))
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
                .clip(CircleShape),
            placeholder = painterResource(R.drawable.ic_placeholder),
            contentDescription = null,
            fallback = painterResource(R.drawable.ic_placeholder),
        )
        Text(
            text = actor.name,
            maxLines = 1,
            color = MBTheme.colors.white,
            overflow = TextOverflow.Clip,
            modifier = Modifier
                .fillMaxWidth()
                .basicMarquee()
        )
    }
}