package com.swkang.booksearch.base.di

import com.swkang.model.domain.booksearch.BookSearchReducer
import com.swkang.model.domain.common.message.MessageReducer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
@Module
@InstallIn(ApplicationComponent::class)
class ReducerModule {

    @Singleton
    @Provides
    fun provideMessageReducer(): MessageReducer {
        return MessageReducer()
    }

    @Singleton
    @Provides
    fun provideBookSearchReducer(): BookSearchReducer {
        return BookSearchReducer()
    }

}