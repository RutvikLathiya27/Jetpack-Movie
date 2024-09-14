package com.example.moviebox.data.datasource.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.moviebox.data.datasource.models.MovieItem
import com.example.moviebox.data.datasource.models.moview_detail.MovieDetailModel
import com.example.moviebox.data.datasource.paging.NowPlayingPagingDataSource
import com.example.moviebox.data.datasource.paging.PopularPagingDataSource
import com.example.moviebox.data.datasource.paging.TopRatedPagingDataSource
import com.example.moviebox.data.datasource.paging.UpcomingPagingDataSource
import com.example.moviebox.data.datasource.remote.ApiService
import com.example.moviebox.utils.DataState
import com.piashcse.hilt_mvvm_compose_movie.data.model.artist.Artist
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: ApiService
) : MovieRepositoryInterface {

    override suspend fun movieDetail(movieId: Int): Flow<DataState<MovieDetailModel>> = flow {
        emit(DataState.Loading)
        try {
            val movieDetail = apiService.movieDetail(movieId)
            Log.e("TAG", "Movie load >>>> succ : $movieDetail")
            emit(DataState.Success(movieDetail))
        } catch (e: Exception){
            Log.e("TAG", "Movie load >>>> fail : $e")
            emit(DataState.Error(e))
        }
    }

    override suspend fun getMovieCast(movieId: Int): Flow<DataState<Artist>> = flow {
        emit(DataState.Loading)
        try {
            val lstArtist = apiService.getCast(movieId)
            emit(DataState.Success(lstArtist))
        } catch (e: Exception){
            emit(DataState.Error(e))
        }
    }


    override fun nowPlayingPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>> = Pager(
        pagingSourceFactory = { NowPlayingPagingDataSource(apiService, genreId) },
        config = PagingConfig(1)
    ).flow

    override fun popularMoviesPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>> = Pager(
        pagingSourceFactory = { PopularPagingDataSource(apiService, genreId) },
        config = PagingConfig(1)
    ).flow

    override fun topRatedMoviesPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>> = Pager(
        pagingSourceFactory = { TopRatedPagingDataSource(apiService, genreId) },
        config = PagingConfig(1)
    ).flow

    override fun upcomingMoviesPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>> = Pager(
        pagingSourceFactory = { UpcomingPagingDataSource(apiService, genreId) },
        config = PagingConfig(1)
    ).flow

}