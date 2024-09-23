package com.example.moviebox.ui.screens.movie_detail_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviebox.data.models.artist.Artist
import com.example.moviebox.data.models.moview_detail.MovieDetailModel
import com.example.moviebox.data.repository.MovieRepository
import com.example.moviebox.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repositoryInterface: MovieRepository
) : ViewModel() {

    private val _movieDetail = MutableStateFlow<MovieDetailModel?>(null)
    val movieDetail: StateFlow<MovieDetailModel?> = _movieDetail.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d("ISHAN", ": " + throwable.message)
    }

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            repositoryInterface.movieDetail(movieId).collect {
                when (it) {
                    is DataState.Loading -> {
                        _isLoading.value = true
                    }

                    is DataState.Success -> {
                        _movieDetail.value = it.data
                        _isLoading.value = false
                    }

                    is DataState.Error -> {
                        _isLoading.value = false
                    }
                }
            }
        }
    }

    fun getMovieCast(movieId: Int): MutableStateFlow<Artist?> {
        val artists: MutableStateFlow<Artist?> = MutableStateFlow(null)

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            repositoryInterface.getMovieCast(movieId).collect {
                if (it is DataState.Success) {
                    artists.value = it.data
                }
            }
        }

        return artists
    }
}