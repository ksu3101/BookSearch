package com.swkang.booksearch.base.di

import com.swkang.model.domain.booksearch.BookSearchReducer
import com.swkang.model.domain.common.message.MessageReducer
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
@Module
class ReducerModule {

    @Provides
    @Singleton
    fun provideMessageReducer(): MessageReducer {
        return MessageReducer()
    }

    @Provides
    @Singleton
    fun provideBookSearchReducer(): BookSearchReducer {
        return BookSearchReducer()
    }

}