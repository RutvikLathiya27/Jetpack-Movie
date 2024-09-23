package com.example.moviebox.ui.screens.upcoming_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviebox.data.models.MovieItem
import com.example.moviebox.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class UpcomingViewModel @Inject constructor(repositoryInterface: MovieRepository) : ViewModel() {

    val upcomingMovies: Flow<PagingData<MovieItem>> =
        repositoryInterface.upcomingMoviesPagingDataSource("All").cachedIn(viewModelScope)
}