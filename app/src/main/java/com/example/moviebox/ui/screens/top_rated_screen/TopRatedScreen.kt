package com.example.moviebox.ui.screens.top_rated_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviebox.data.datasource.models.MovieItem
import com.example.moviebox.ui.composable.MovieItemList

@Composable
fun TopRatedScreen(modifier: Modifier, navController: NavController) {

    val topRatedViewModel = hiltViewModel<TopRatedViewModel>()
    val topRatedDataStream: LazyPagingItems<MovieItem> =
        topRatedViewModel.topRatedMovies.collectAsLazyPagingItems()

    Column {
        MovieItemList(modifier = modifier, dataStream = topRatedDataStream){}
    }

}