package com.mbh.moviebrowser.data.source.remote

import com.mbh.moviebrowser.data.source.remote.model.genre.GenreResponseData
import com.mbh.moviebrowser.data.source.remote.model.movie.MovieResponseData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieBrowserNetworkApi {
    @GET("trending/movie/{time_window}")
    suspend fun getTrendingMovies(
        @Path("time_window") timeWindow: String,
        @Query("language") language: String,
        @Query("api_key") apiKey: String,
    ): MovieResponseData

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("language") language: String,
        @Query("api_key") apiKey: String,
    ): GenreResponseData
}
