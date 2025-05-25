package com.mbh.moviebrowser.di

import com.google.gson.Gson
import com.mbh.moviebrowser.BuildConfig
import com.mbh.moviebrowser.core.data.source.remote.addLoggingInterceptor
import com.mbh.moviebrowser.data.source.remote.MovieBrowserNetworkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val TIMEOUT_IN_SECONDS = 30L

    @Singleton
    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
        gson: Gson,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addLoggingInterceptor()
            .connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()

    @Singleton
    @Provides
    fun provideMovieBrowserNetworkApi(retrofit: Retrofit): MovieBrowserNetworkApi =
        retrofit.create(MovieBrowserNetworkApi::class.java)
}
