package com.example.moviebox.ui.screens.now_playing_screen

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
class NowPlayingViewModel @Inject constructor(
    private val repositoryInterface: MovieRepository
) : ViewModel() {

    val nowPlayingMovies: Flow<PagingData<MovieItem>> =
        repositoryInterface.nowPlayingPagingDataSource("All").cachedIn(viewModelScope)

}
