package com.mbh.moviebrowser.data.repository

import com.mbh.moviebrowser.data.repository.model.MovieData
import com.mbh.moviebrowser.data.repository.model.toGenreList
import com.mbh.moviebrowser.data.repository.model.toGenreData
import com.mbh.moviebrowser.data.repository.model.toMovieData
import com.mbh.moviebrowser.data.repository.model.toMovieList
import com.mbh.moviebrowser.data.source.remote.MovieBrowserNetworkDataSource
import javax.inject.Inject

class DefaultMovieBrowserRepository @Inject constructor(
    private val movieBrowserNetworkDataSource: MovieBrowserNetworkDataSource,
) : MovieBrowserRepository {

    override suspend fun getTrendingMovies(
        timeWindow: String,
        language: String,
    ) : Result<List<MovieData>> {
        val genres = movieBrowserNetworkDataSource.getGenres(
            language = language,
        ).mapCatching {
            it.toGenreList()
        }

        val movies = movieBrowserNetworkDataSource.getTrendingMovies(
            timeWindow = timeWindow,
            language = language,
        ).mapCatching {
            it.toMovieList()
        }

        if (movies.isSuccess && genres.isSuccess) {
            val movieList = movies.getOrNull()
            val genreList = genres.getOrNull()

            val moviesWithGenres = movieList!!.items.map { movie ->
                movie.toMovieData(genreList!!.items.map { it.toGenreData() })
            }

            return Result.success(moviesWithGenres)
        } else {
            return Result.failure(Exception("Something went wrong"))
        }
    }
}
