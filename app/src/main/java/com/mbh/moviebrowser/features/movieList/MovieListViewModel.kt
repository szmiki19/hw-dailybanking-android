package com.mbh.moviebrowser.features.movieList

import androidx.lifecycle.ViewModel
import com.mbh.moviebrowser.data.repository.model.MovieData
import com.mbh.moviebrowser.data.store.MovieStore

class MovieListViewModel(
    private val movieStore: MovieStore,
) : ViewModel() {
    val movies = movieStore.movies
    val isLoading = movieStore.isLoading
    val isSuccess = movieStore.isSuccess

    fun storeMovieForNavigation(movie: MovieData) {
        movieStore.detailsId.value = movie.id
    }
}
