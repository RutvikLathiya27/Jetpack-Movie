package com.example.moviebox.data.datasource.repository

import android.provider.ContactsContract.Data
import androidx.paging.PagingData
import com.example.moviebox.data.datasource.models.MovieItem
import com.example.moviebox.data.datasource.models.moview_detail.MovieDetailModel
import com.example.moviebox.utils.DataState
import com.piashcse.hilt_mvvm_compose_movie.data.model.artist.Artist
import kotlinx.coroutines.flow.Flow

interface MovieRepositoryInterface {

    suspend fun movieDetail(movieId: Int): Flow<DataState<MovieDetailModel>>
    suspend fun getMovieCast(movieId: Int) : Flow<DataState<Artist>>

    fun nowPlayingPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>>
    fun popularMoviesPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>>
    fun topRatedMoviesPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>>
    fun upcomingMoviesPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>>

}