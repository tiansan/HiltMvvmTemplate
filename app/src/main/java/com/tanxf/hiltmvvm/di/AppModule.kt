package com.tanxf.hiltmvvm.di

import com.tanxf.hiltmvvm.BuildConfig
import com.tanxf.hiltmvvm.data.api.ApiHelper
import com.tanxf.hiltmvvm.data.api.ApiHelperImpl
import com.tanxf.hiltmvvm.data.api.ApiService
import com.tanxf.hiltmvvm.http.AddHeaderInterceptor
import com.tanxf.hiltmvvm.http.ReceivedCookiesInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ReceivedCookiesInterceptor())
            .addInterceptor(AddHeaderInterceptor())
            .build()
    } else OkHttpClient
        .Builder()
        .addInterceptor(ReceivedCookiesInterceptor())
        .addInterceptor(AddHeaderInterceptor())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        BASE_URL: String
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

}