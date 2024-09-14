package com.example.moviebox.ui.screens.upcoming_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviebox.ui.composable.MovieItemList
import com.example.moviebox.ui.navigations.Screens

@Composable
fun UpComingMovieScreen(modifier: Modifier, navController: NavController){

    val upcomingViewModel = hiltViewModel<UpcomingViewModel>()
    val upcomingMovie = upcomingViewModel.upcomingMovies.collectAsLazyPagingItems()

    Column {
        MovieItemList(modifier = modifier, dataStream = upcomingMovie){}
    }

}