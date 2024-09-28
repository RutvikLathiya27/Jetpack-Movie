package com.example.moviebox.ui.screens.search_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviebox.R
import com.example.moviebox.data.models.MovieItem
import com.example.moviebox.data.remote.ApiURL
import com.example.moviebox.ui.composable.CircularProgressBar
import com.example.moviebox.ui.navigations.Screens
import com.example.moviebox.ui.screens.movie_detail_screen.MovieDetailScreen
import com.example.moviebox.ui.theme.MBTheme

@Composable
fun SearchScreen(navController: NavHostController) {

    val searchMovieViewModel = hiltViewModel<SearchViewModel>()
    val searchMovie by searchMovieViewModel.searchMovie.collectAsStateWithLifecycle()
    val isLoading by searchMovieViewModel.isLoading.collectAsStateWithLifecycle()
    var searchText by remember { mutableStateOf("") }


    LaunchedEffect(searchText) {
        if (searchText.isNotEmpty()) {
            searchMovieViewModel.searchMovie(searchText)
        }
    }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MBTheme.colors.secondary,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MBTheme.colors.primary)
                    .statusBarsPadding()
                    .padding(horizontal = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = searchText,
                    onValueChange = { it ->
                        searchText = it
                    },
                    placeholder = {
                        Text("Search Movies")
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 10.dp, bottom = 10.dp, end = 10.dp)
                        .clip(RoundedCornerShape(20.dp)),
                )
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null,
                    modifier = Modifier
                        .size(28.dp),
                    tint = MBTheme.colors.lightBlue
                )

            }
        },
    ) { innerPadding ->
        if (isLoading) {
            CircularProgressBar()
        } else {
            if (searchMovie?.results?.isEmpty() == true || searchMovie?.results == null) {
                Text(
                    "Search Not Found!!!",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = MBTheme.colors.white,
                )
            } else {
                LazyColumn(
                    modifier = Modifier.padding(innerPadding)
                ) {
                    val lstSearchResult = searchMovie!!.results
                    items(lstSearchResult.size) { index ->
                        val item = lstSearchResult[index]
                        searchItem(item){
                            navController.navigate(Screens.MovieDetailScreen(item.id))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun searchItem(movieItem: MovieItem,
                       onClick: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp, vertical = 5.dp)
            .background(color = MBTheme.colors.black80)
            .clip(RoundedCornerShape(18.dp))
            .clickable {
                onClick()
            }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(ApiURL.IMAGE_URL.plus(movieItem.posterPath))
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(180.dp)
                .padding(start = 5.dp, top = 5.dp, bottom = 5.dp)
                .fillMaxWidth(0.3f)
                .clip(RoundedCornerShape(16.dp)),
            placeholder = painterResource(R.drawable.ic_placeholder),
            contentDescription = null,
            fallback = painterResource(R.drawable.ic_placeholder),
        )
        Column(
            modifier = Modifier
                .padding(7.dp)
        ) {
            Text(
                text = movieItem.title,
                maxLines = 1,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = MBTheme.colors.lightBlue,
                overflow = TextOverflow.Clip,
                modifier = Modifier
                    .fillMaxWidth()
                    .basicMarquee()
            )
            Box(Modifier.height(5.dp))
            Text(
                text = String.format("%.1f", movieItem.voteAverage),
                maxLines = 3,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = MBTheme.colors.lightBlue,
                overflow = TextOverflow.Clip,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Box(Modifier.height(5.dp))
            Text(
                text = movieItem.overview,
                maxLines = 3,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Thin
                ),
                color = MBTheme.colors.white,
                overflow = TextOverflow.Clip,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }

}