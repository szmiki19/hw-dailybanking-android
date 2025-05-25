package com.mbh.moviebrowser.core.data.source.remote

import com.mbh.moviebrowser.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

fun OkHttpClient.Builder.addLoggingInterceptor(
    logLevel: HttpLoggingInterceptor.Level =
        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE,
): OkHttpClient.Builder =
    addNetworkInterceptor(
        HttpLoggingInterceptor().apply {
            level = logLevel
        }
    )
