package com.example.moviebox.ui.screens.search_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviebox.data.models.BaseModel
import com.example.moviebox.data.repository.MovieRepository
import com.example.moviebox.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repositoryInterface: MovieRepository
) : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d("ISHAN", ": " + throwable.message)
    }

    private val _searchMovie = MutableStateFlow<BaseModel?>(null)
    val searchMovie: StateFlow<BaseModel?> = _searchMovie.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    var searchMovieJob : Job? = null

    fun searchMovie(searchText: String) {
        searchMovieJob?.cancel()
        searchMovieJob = viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            repositoryInterface.getSearchResult(searchText).collect {
                when (it) {
                    is DataState.Loading -> {
                        _isLoading.value = true
                    }

                    is DataState.Success -> {
                        _searchMovie.value = it.data
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