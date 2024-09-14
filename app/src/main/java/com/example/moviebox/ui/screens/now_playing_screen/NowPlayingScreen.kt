package com.example.moviebox.ui.screens.now_playing_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviebox.data.datasource.models.MovieItem
import com.example.moviebox.ui.composable.CircularIndeterminateProgressBar
import com.example.moviebox.ui.composable.MovieItemList
import com.example.moviebox.ui.navigations.Screens

@Composable
fun NowPlayingScreen(modifier: Modifier, navController: NavHostController) {

    val nowPlayViewModel = hiltViewModel<NowPlayingViewModel>()
    val dataStream: LazyPagingItems<MovieItem> =
        nowPlayViewModel.nowPlayingMovies.collectAsLazyPagingItems()

    MovieItemList(modifier = modifier, dataStream = dataStream) {
        navController.navigate(Screens.MovieDetailScreen(it))
    }


}

