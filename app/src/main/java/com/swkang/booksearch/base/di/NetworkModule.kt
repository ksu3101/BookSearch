package com.swkang.booksearch.base.di

import com.swkang.booksearch.BookSearchApplication
import com.swkang.booksearch.base.network.AddKakaoAkHeaderIntercepter
import com.swkang.booksearch.repositories.booksearch.BookSearchApi
import com.swkang.booksearch.repositories.booksearch.BookSearchRepositoryImpl
import com.swkang.booksearch.repositories.booksearch.BookSearchRepositoryMockImpl
import com.swkang.model.domain.booksearch.BookSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {
    private val TIMEOUT_SEC = 10L

    @Singleton
    @Provides
    fun provieOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
            .addInterceptor(AddKakaoAkHeaderIntercepter())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl("https://dapi.kakao.com/v3/")
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideBookSearchApi(retrofit: Retrofit): BookSearchApi {
        return retrofit.create(BookSearchApi::class.java)
    }

    @Singleton
    @Provides
    fun provideBookSearchRepository(context: BookSearchApplication, api: BookSearchApi): BookSearchRepository {
        return if (context.isMock) BookSearchRepositoryMockImpl() else  BookSearchRepositoryImpl(api)
    }

}