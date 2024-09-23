package com.example.moviebox.ui.screens.now_playing_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviebox.ui.composable.MovieItemList
import com.example.moviebox.ui.navigations.Screens

@Composable
fun NowPlayingScreen(modifier: Modifier, navController: NavHostController) {
    val nowPlayViewModel = hiltViewModel<NowPlayingViewModel>()
    val dataStream = nowPlayViewModel.nowPlayingMovies.collectAsLazyPagingItems()

    MovieItemList(modifier = modifier, dataStream = dataStream) {
        navController.navigate(Screens.MovieDetailScreen(it))
    }
}

