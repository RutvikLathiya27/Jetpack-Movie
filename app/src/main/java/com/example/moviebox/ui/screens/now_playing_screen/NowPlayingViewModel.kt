package com.example.moviebox.ui.screens.now_playing_screen

import androidx.lifecycle.ViewModel
import com.example.moviebox.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NowPlayingViewModel @Inject constructor(val repositoryInterface: MovieRepository) : ViewModel() {

    val nowPlayingMovies = repositoryInterface.nowPlayingPagingDataSource("All")

}
