package com.example.moviebox.data.models.artist


import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("cast")
    val cast: List<Cast>,
    @SerializedName("id")
    val id: Int
)