package com.example.moviebox.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.moviebox.data.models.MovieItem
import com.example.moviebox.data.models.moview_detail.MovieDetailModel
import com.example.moviebox.data.paging.NowPlayingPagingDataSource
import com.example.moviebox.data.paging.PopularPagingDataSource
import com.example.moviebox.data.paging.TopRatedPagingDataSource
import com.example.moviebox.data.paging.UpcomingPagingDataSource
import com.example.moviebox.data.remote.ApiService
import com.example.moviebox.utils.DataState
import com.example.moviebox.data.models.artist.Artist
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MovieRepository {

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