package com.mbh.moviebrowser.data.store

import com.mbh.moviebrowser.data.repository.MovieBrowserRepository
import com.mbh.moviebrowser.data.repository.model.MovieData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieStore(
    private val movieBrowserRepository: MovieBrowserRepository,
    private val coroutineScope: CoroutineScope,
    private val timeWindow: String,
    private val language: String,
) {
    val movies: MutableStateFlow<List<MovieData>> = MutableStateFlow(emptyList())
    val detailsId: MutableStateFlow<Long> = MutableStateFlow(-1)
    val isLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isSuccess: MutableStateFlow<Boolean> = MutableStateFlow(true)

    init {
        initMovieStore()
    }

    private fun initMovieStore() {
        coroutineScope.launch {
            movieBrowserRepository.getTrendingMovies(
                timeWindow = timeWindow,
                language = language,
            ).onSuccess { result ->
                movies.update {
                    result
                }
                isLoading.value = false
            }.onFailure {
                isLoading.value = false
                isSuccess.value = false
            }
        }
    }
}
