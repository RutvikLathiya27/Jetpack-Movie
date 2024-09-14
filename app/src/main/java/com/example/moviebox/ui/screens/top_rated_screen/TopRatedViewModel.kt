package com.example.moviebox.ui.screens.top_rated_screen

import androidx.lifecycle.ViewModel
import com.example.moviebox.data.datasource.repository.MovieRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopRatedViewModel @Inject constructor(repositoryInterface: MovieRepositoryInterface) : ViewModel(){

    val topRatedMovies = repositoryInterface.topRatedMoviesPagingDataSource("All")

}