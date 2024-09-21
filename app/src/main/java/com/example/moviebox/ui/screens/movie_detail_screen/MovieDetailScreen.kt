package com.example.moviebox.ui.screens.movie_detail_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.moviebox.R
import com.example.moviebox.data.remote.ApiURL
import com.example.moviebox.ui.composable.MovieBoxAppBar
import com.example.moviebox.ui.theme.MBTheme
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MovieDetailScreen(movieId: Int, navController: NavHostController) {

    val movieDetailViewModel = hiltViewModel<MovieDetailViewModel>()
    val movieDetail = movieDetailViewModel.movieDetail.collectAsStateWithLifecycle()
    val isLoading = movieDetailViewModel.isLoading.collectAsStateWithLifecycle();
    val scrollState = rememberScrollState()


    LaunchedEffect(key1 = Unit) {
        movieDetailViewModel.getMovieDetail(movieId)
    }

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(MBTheme.colors.secondary),
        topBar = { MovieBoxAppBar(title = "Movie Details") }) { innerPadding ->
        if (isLoading.value) {
            CircularProgressIndicator(color = Color.Gray, modifier = Modifier.padding(innerPadding))
        } else {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .background(MBTheme.colors.secondary)
                    .fillMaxSize()
            ) {

                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.45f)
                ) {
                    val (topBox, bgImage, mainImage, title, release, rowData) = createRefs()

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(.70f)
                            .constrainAs(topBox) {
                                top.linkTo(parent.top)
                                end.linkTo(parent.end)
                                start.linkTo(parent.start)
                            }
                    )

                    GlideImage(
                        imageModel = ApiURL.IMAGE_URL.plus(movieDetail!!.value!!.backdropPath),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .constrainAs(bgImage) {
                                top.linkTo(topBox.top)
                                start.linkTo(topBox.start)
                                end.linkTo(topBox.end)
                                bottom.linkTo(topBox.bottom)
                                height = Dimension.fillToConstraints
                            }
                            .fillMaxWidth(),
                    )

                    GlideImage(
                        imageModel = ApiURL.IMAGE_URL.plus(movieDetail.value!!.posterPath),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .constrainAs(mainImage) {
                                top.linkTo(topBox.bottom)
                                bottom.linkTo(topBox.bottom)
                                end.linkTo(parent.end)
                            }
                            .padding(end = 8.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .aspectRatio((1f / 1.33f))
                    )

                    Text(
                        text = movieDetail.value!!.title,
                        fontSize = 18.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Clip,
                        color = MBTheme.colors.white,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp, start = 12.dp, end = 12.dp)
                            .basicMarquee()
                            .constrainAs(title) {
                                top.linkTo(topBox.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(mainImage.start)
                                width = Dimension.fillToConstraints
                            }
                    )

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp, start = 12.dp, end = 12.dp)
                        .constrainAs(rowData) {
                            top.linkTo(title.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(mainImage.start)
                            width = Dimension.fillToConstraints
                        }) {

                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_clock),
                            null
                        )
                        Text(
                            text = movieDetail.value!!.runtime.toString().plus(" min"),
                            fontSize = 14.sp,
                            color = MBTheme.colors.white,

                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 4.dp)
                        )

                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_rating),
                            null
                        )
                        Text(
                            text = String.format("%.1f", movieDetail.value!!.rating),
                            fontSize = 14.sp,
                            color = MBTheme.colors.white,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 4.dp)
                        )

                    }

                    Text(text = "Release on : ${movieDetail.value!!.releaseDate}",
                        color = MBTheme.colors.white,
                        modifier = Modifier
                            .padding(start = 12.dp, top = 5.dp)
                            .constrainAs(release) {
                                start.linkTo(parent.start)
                                top.linkTo(rowData.bottom)
                            })

                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, start = 10.dp, end = 10.dp)
                        .height(65.dp)
                        .verticalScroll(scrollState)
                ) {
                    Text(
                        text = movieDetail.value!!.overview,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }

            }
        }
    }
}

@Composable
fun getActorsList(movieId: Int) {

    LaunchedEffect(Unit) {

    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 18.dp)) {
        Text(text = "Casts", color = MBTheme.colors.white, modifier = Modifier.fillMaxWidth())
    }

}