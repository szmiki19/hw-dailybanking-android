package com.mbh.moviebrowser.di

import com.mbh.moviebrowser.data.source.remote.DefaultMovieBrowserNetworkDataSource
import com.mbh.moviebrowser.data.source.remote.MovieBrowserNetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NetworkDataSourceModule {

    @Binds
    fun bindMovieBrowserNetworkDataSource(
        defaultMovieBrowserNetworkDataSource: DefaultMovieBrowserNetworkDataSource,
    ): MovieBrowserNetworkDataSource
}
