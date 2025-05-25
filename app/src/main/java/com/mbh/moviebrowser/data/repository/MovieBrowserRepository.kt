package com.mbh.moviebrowser.data.repository

import com.mbh.moviebrowser.data.repository.model.MovieData

interface MovieBrowserRepository {
    suspend fun getTrendingMovies(
        timeWindow: String,
        language: String,
    ): Result<List<MovieData>>
}
