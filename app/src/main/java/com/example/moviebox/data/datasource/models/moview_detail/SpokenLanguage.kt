package com.example.moviebox.data.datasource.models.moview_detail

import com.google.gson.annotations.SerializedName

data class SpokenLanguage(
    @SerializedName("english_name")
    val englishName : String,
    @SerializedName("iso_639_1")
    val languageCode : String
)
