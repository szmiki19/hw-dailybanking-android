package com.mbh.moviebrowser.data.source.remote

import com.mbh.moviebrowser.data.source.remote.model.genre.GenreResponseData
import com.mbh.moviebrowser.data.source.remote.model.movie.MovieResponseData

interface MovieBrowserNetworkDataSource {
    suspend fun getTrendingMovies(
        timeWindow: String,
        language: String,
    ): Result<MovieResponseData>

    suspend fun getGenres(
        language: String,
    ): Result<GenreResponseData>
}
