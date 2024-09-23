package com.example.moviebox.ui.screens.top_rated_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviebox.ui.composable.MovieItemList
import com.example.moviebox.ui.navigations.Screens

@Composable
fun TopRatedScreen(modifier: Modifier, navController: NavController) {
    val topRatedViewModel = hiltViewModel<TopRatedViewModel>()
    val topRatedDataStream = topRatedViewModel.topRatedMovies.collectAsLazyPagingItems()

    MovieItemList(modifier = modifier, dataStream = topRatedDataStream) {
        navController.navigate(Screens.MovieDetailScreen(it))
    }
}