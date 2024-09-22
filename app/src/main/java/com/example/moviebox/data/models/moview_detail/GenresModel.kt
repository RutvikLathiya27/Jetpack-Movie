package com.example.moviebox.data.models.moview_detail

import com.google.gson.annotations.SerializedName

data class GenresModel(
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String
)
