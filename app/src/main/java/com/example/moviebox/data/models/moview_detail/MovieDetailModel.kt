package com.example.moviebox.data.models.moview_detail

import com.google.gson.annotations.SerializedName

data class MovieDetailModel(
    @SerializedName("backdrop_path")
    val backdropPath : String,
    @SerializedName("budget")
    val budget : Int,
    @SerializedName("genres")
    val genres : List<GenresModel>,
    @SerializedName("original_language")
    val language : String,
    @SerializedName("original_title")
    val title : String,
    @SerializedName("overview")
    val overview : String,
    @SerializedName("poster_path")
    val posterPath : String,
    @SerializedName("release_date")
    val releaseDate : String,
    @SerializedName("revenue")
    val revenue : Int,
    @SerializedName("runtime")
    val runtime : Int,
//    @SerializedName("spoken_languages")
//    val spokenLanguage : SpokenLanguage,
    @SerializedName("vote_average")
    val rating : Float
)