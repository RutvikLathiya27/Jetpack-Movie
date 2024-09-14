package com.example.moviebox.ui.screens.upcoming_screen

import androidx.lifecycle.ViewModel
import com.example.moviebox.data.datasource.repository.MovieRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpcomingViewModel @Inject constructor(repositoryInterface: MovieRepositoryInterface) : ViewModel() {

    val upcomingMovies = repositoryInterface.upcomingMoviesPagingDataSource("All")
}