package com.mbh.moviebrowser.di

import com.mbh.moviebrowser.data.repository.DefaultMovieBrowserRepository
import com.mbh.moviebrowser.data.repository.MovieBrowserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindMovieBrowserRepository(
        defaultMovieBrowserRepository: DefaultMovieBrowserRepository
    ): MovieBrowserRepository
}
