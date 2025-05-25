package com.mbh.moviebrowser.data.source.remote.model.movie


import com.google.gson.annotations.SerializedName

data class MovieResponseData(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: ArrayList<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int,
)
