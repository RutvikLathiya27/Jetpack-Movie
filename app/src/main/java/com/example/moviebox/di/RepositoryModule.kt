package com.example.moviebox.di

import com.example.moviebox.data.datasource.remote.ApiService
import com.example.moviebox.data.datasource.repository.MovieRepository
import com.example.moviebox.data.datasource.repository.MovieRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideMoviesRepository(apiService: ApiService) : MovieRepositoryInterface{
        return MovieRepository(apiService)
    }

}