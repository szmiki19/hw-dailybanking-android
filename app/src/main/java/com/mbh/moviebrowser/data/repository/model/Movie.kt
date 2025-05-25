package com.mbh.moviebrowser.data.repository.model


import com.mbh.moviebrowser.data.source.remote.model.movie.Movie
import com.mbh.moviebrowser.data.source.remote.model.movie.MovieResponseData

private const val COVER_BASE_URL = "https://image.tmdb.org/t/p/w500/"

data class MovieList(
    val items: ArrayList<Movie>,
)

data class MovieData(
    val id: Long,
    val title: String,
    val genres: String,
    val overview: String,
    val coverUrl: String,
    val releaseDate: String,
    val rating: Float,
    val isFavorite: Boolean,
)

fun MovieResponseData.toMovieList(): MovieList {
    return MovieList(
        items = results,
    )
}

fun Movie.toMovieData(genres: List<GenreData>): MovieData {
    return MovieData(
        id = id.toLong(),
        title = title,
        genres = getGenreString(genres, genreIds),
        overview = overview,
        coverUrl = COVER_BASE_URL + posterPath,
        releaseDate = releaseDate,
        rating = voteAverage.toFloat(),
        isFavorite = false,
    )
}

private fun getGenreString(genres: List<GenreData>, genreIds: List<Int>): String {
    val genreList: MutableList<String> = mutableListOf()

    genreIds.forEach { genreId ->
        val genre = genres.find { it.id == genreId }?.name
        genre?.let {
            genreList.add(genre)
        }
    }

    return genreList.joinToString(", ")
}
