package com.example.moviebox.data.repository

import androidx.paging.PagingData
import com.example.moviebox.data.models.MovieItem
import com.example.moviebox.data.models.moview_detail.MovieDetailModel
import com.example.moviebox.utils.DataState
import com.example.moviebox.data.models.artist.Artist
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun movieDetail(movieId: Int): Flow<DataState<MovieDetailModel>>
    suspend fun getMovieCast(movieId: Int) : Flow<DataState<Artist>>

    fun nowPlayingPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>>
    fun popularMoviesPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>>
    fun topRatedMoviesPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>>
    fun upcomingMoviesPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>>

}