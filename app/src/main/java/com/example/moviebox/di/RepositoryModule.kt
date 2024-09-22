package com.example.moviebox.di

import com.example.moviebox.data.remote.ApiService
import com.example.moviebox.data.repository.MovieRepositoryImpl
import com.example.moviebox.data.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideMoviesRepository(apiService: ApiService): MovieRepository {
        return MovieRepositoryImpl(apiService)
    }

}