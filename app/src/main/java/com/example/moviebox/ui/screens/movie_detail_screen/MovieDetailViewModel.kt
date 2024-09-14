package com.example.moviebox.ui.screens.movie_detail_screen

import android.provider.ContactsContract.Data
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviebox.data.datasource.models.moview_detail.MovieDetailModel
import com.example.moviebox.data.datasource.repository.MovieRepositoryInterface
import com.example.moviebox.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val repositoryInterface: MovieRepositoryInterface) : ViewModel() {

    private val _movieDetail = MutableStateFlow<MovieDetailModel?>(null)
    val movieDetail get() = _movieDetail.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(true)
    val isLoading get() = _isLoading.asStateFlow()

    fun getMovieDetail(movieId : Int){
        Log.e("TAG", "result >>>>>>> called")
        viewModelScope.launch {
            repositoryInterface.movieDetail(movieId).collect {
                Log.e("TAG", "result >>>>>>> ${it}")
                when(it){
                    is DataState.Loading -> {
                        _isLoading.value = true
                    }
                    is DataState.Success -> {
                        _movieDetail.value =it.data
                        _isLoading.value = false
                    }
                    is DataState.Error -> {
                        _isLoading.value = false
                    }
                }
            }
        }
    }

    fun getMovieCast(movieId: Int){
        viewModelScope.launch {
            repositoryInterface.movieDetail(movieId).collect {
                Log.e("TAG", "result >>>>>>> ${it}")
                when(it){
                    is DataState.Loading -> {
                        _isLoading.value = true
                    }
                    is DataState.Success -> {
                        _movieDetail.value =it.data
                        _isLoading.value = false
                    }
                    is DataState.Error -> {
                        _isLoading.value = false
                    }
                }
            }
        }
    }


}