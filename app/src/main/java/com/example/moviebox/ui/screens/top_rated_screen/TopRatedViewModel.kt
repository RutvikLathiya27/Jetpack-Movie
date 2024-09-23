package com.example.moviebox.ui.screens.top_rated_screen

import android.util.Log
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
class TopRatedViewModel @Inject constructor(repositoryInterface: MovieRepository) : ViewModel() {

    val topRatedMovies: Flow<PagingData<MovieItem>> =
        repositoryInterface.topRatedMoviesPagingDataSource("All").cachedIn(viewModelScope)

}