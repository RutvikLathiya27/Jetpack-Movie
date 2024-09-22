package com.example.moviebox.ui.screens.movie_detail_screen

import android.provider.ContactsContract.Data
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviebox.data.datasource.models.moview_detail.MovieDetailModel
import com.example.moviebox.data.datasource.repository.MovieRepositoryInterface
import com.example.moviebox.utils.DataState
import com.piashcse.hilt_mvvm_compose_movie.data.model.artist.Artist
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val repositoryInterface: MovieRepositoryInterface) :
    ViewModel() {

    private val _movieDetail = MutableStateFlow<MovieDetailModel?>(null)
    val movieDetail get() = _movieDetail.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(true)
    val isLoading get() = _isLoading.asStateFlow()

    fun getMovieDetail(movieId: Int) {
        Log.e("TAG", "result >>>>>>> called")
        viewModelScope.launch {
            repositoryInterface.movieDetail(movieId).collect {
                Log.e("TAG", "result >>>>>>> ${it}")
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

        viewModelScope.launch {
            repositoryInterface.getMovieCast(movieId).collect {
                when (it) {
                    is DataState.Loading -> {
                    }

                    is DataState.Success -> {
                        artists.value= it . data
                    }

                    is DataState.Error -> {
                    }
                }
            }
        }

        return artists
    }


}