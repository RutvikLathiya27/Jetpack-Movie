package com.example.moviebox.data.remote

import com.example.moviebox.data.models.BaseModel
import com.example.moviebox.data.models.moview_detail.MovieDetailModel
import com.example.moviebox.data.models.artist.Artist
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    suspend fun nowPlayingMovieList(
        @Query("page") page: Int,
        @Query("with_genres") genreId: String?,
        @Query("api_key") api_key: String = ApiURL.API_KEY
    ): BaseModel

    @GET("movie/popular")
    suspend fun popularMovieList(
        @Query("page") page: Int,
        @Query("with_genres") genreId: String?,
        @Query("api_key") api_key: String = ApiURL.API_KEY
    ): BaseModel

    @GET("movie/top_rated")
    suspend fun topRatedMovieList(
        @Query("page") page: Int,
        @Query("with_genres") genreId: String?,
        @Query("api_key") api_key: String = ApiURL.API_KEY
    ): BaseModel

    @GET("movie/upcoming")
    suspend fun upcomingMovieList(
        @Query("page") page: Int,
        @Query("with_genres") genreId: String?,
        @Query("api_key") api_key: String = ApiURL.API_KEY
    ): BaseModel

    @GET("movie/{movie_id}")
    suspend fun movieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String = ApiURL.API_KEY
    ): MovieDetailModel

    @GET("movie/{movie_id}/credits")
    suspend fun getCast(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String = ApiURL.API_KEY
    ): Artist

    @GET("search/movie?include_adult=true&page=1")
    suspend fun getSearchResult(
        @Query("query") searchKey : String,
        @Query("api_key") api_key: String = ApiURL.API_KEY
    ) : BaseModel


}