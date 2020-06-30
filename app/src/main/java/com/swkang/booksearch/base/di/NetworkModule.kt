package com.swkang.booksearch.base.di

import com.swkang.booksearch.base.network.AddKakaoAkHeaderIntercepter
import com.swkang.booksearch.repositories.booksearch.BookSearchApi
import com.swkang.booksearch.repositories.booksearch.BookSearchRepositoryImpl
import com.swkang.model.domain.booksearch.BookSearchRepository
import dagger.Module
import dagger.Provides
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
class NetworkModule {
    private val TIMEOUT_SEC = 10L

    @Provides
    @Singleton
    fun provieOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
            .addInterceptor(AddKakaoAkHeaderIntercepter())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl("https://dapi.kakao.com/v3/")
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideBookSearchApi(retrofit: Retrofit): BookSearchApi {
        return retrofit.create(BookSearchApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBookSearchRepository(api: BookSearchApi): BookSearchRepository {
        return BookSearchRepositoryImpl(api)
    }

}