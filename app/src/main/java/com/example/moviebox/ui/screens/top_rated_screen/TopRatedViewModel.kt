package com.example.moviebox.ui.screens.top_rated_screen

import androidx.lifecycle.ViewModel
import com.example.moviebox.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopRatedViewModel @Inject constructor(repositoryInterface: MovieRepository) : ViewModel(){

    val topRatedMovies = repositoryInterface.topRatedMoviesPagingDataSource("All")

}