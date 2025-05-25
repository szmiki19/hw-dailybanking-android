package com.mbh.moviebrowser.data.source.remote

import com.mbh.moviebrowser.BuildConfig
import com.mbh.moviebrowser.di.IODispatcher
import com.mbh.moviebrowser.data.source.remote.model.genre.GenreResponseData
import com.mbh.moviebrowser.data.source.remote.model.movie.MovieResponseData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultMovieBrowserNetworkDataSource @Inject constructor(
    private val movieBrowserNetworkApi: MovieBrowserNetworkApi,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
): MovieBrowserNetworkDataSource {

    override suspend fun getTrendingMovies(
        timeWindow: String,
        language: String,
    ): Result<MovieResponseData> = withContext(ioDispatcher) {
        runCatching {
            movieBrowserNetworkApi.getTrendingMovies(
                timeWindow = timeWindow,
                language = language,
                apiKey = BuildConfig.API_KEY,
            )
        }
    }

    override suspend fun getGenres(
        language: String,
    ): Result<GenreResponseData> = withContext(ioDispatcher) {
        runCatching {
            movieBrowserNetworkApi.getGenres(
                language = language,
                apiKey = BuildConfig.API_KEY,
            )
        }
    }
}
