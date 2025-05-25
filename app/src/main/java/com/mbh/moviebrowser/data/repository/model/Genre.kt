package com.mbh.moviebrowser.data.repository.model


import com.mbh.moviebrowser.data.source.remote.model.genre.Genre
import com.mbh.moviebrowser.data.source.remote.model.genre.GenreResponseData

data class GenreList(
    val items: ArrayList<Genre>,
)

data class GenreData(
    val id: Int,
    val name: String,
)

fun GenreResponseData.toGenreList(): GenreList {
    return GenreList(
        items = genres,
    )
}

fun Genre.toGenreData(): GenreData {
    return GenreData(
        id = id,
        name = name,
    )
}
