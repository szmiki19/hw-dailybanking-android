package com.mbh.moviebrowser.data.source.remote.model.genre


import com.google.gson.annotations.SerializedName

data class GenreResponseData(
    @SerializedName("genres")
    val genres: ArrayList<Genre>,
)
