package com.example.moviebox.ui.navigations

import kotlinx.serialization.Serializable

sealed class Screens() {

    @Serializable
    data object MainScreen : Screens()
    @Serializable
    data object TopRatingScreen : Screens()
    @Serializable
    data object UpcomingScreen : Screens()

    data object PopularScreen : Screens()

    @Serializable
    data class MovieDetailScreen(val movieId : Int) : Screens()
}

