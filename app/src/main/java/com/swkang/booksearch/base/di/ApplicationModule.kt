package com.swkang.booksearch.base.di

import android.content.Context
import com.swkang.booksearch.base.helper.MessageHelperImpl
import com.swkang.booksearch.base.helper.ResourceHelperImpl
import com.swkang.model.base.helper.MessageHelper
import com.swkang.model.base.helper.ResourceHelper
import com.swkang.model.base.redux.*
import com.swkang.model.domain.booksearch.BookSearchReducer
import com.swkang.model.domain.booksearch.InitializedState
import com.swkang.model.domain.common.message.HandledMessageState
import com.swkang.model.domain.common.message.MessageReducer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
@Module
@InstallIn(ApplicationComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideMiddlewares(): @JvmSuppressWildcards List<MiddleWare<AppState>> {
        return listOf(
            ActionProcessorMiddleware(
                CombinedActionProcessor(
                    listOf()
                )
            )
        )
    }

    @Singleton
    @Provides
    fun provideAppStore(
        messageReducer: MessageReducer,
        bookSearchReducer: BookSearchReducer,
        middlewares: @JvmSuppressWildcards List<MiddleWare<AppState>>
    ): AppStore {
        return AppStore(
            AppState(HandledMessageState, InitializedState),
            AppReducer(
                messageReducer,
                bookSearchReducer
            ),
            middlewares
        )
    }

    @Singleton
    @Provides
    fun provideMessageHelper(@ApplicationContext context: Context): MessageHelper {
        return MessageHelperImpl(context)
    }

    @Singleton
    @Provides
    fun provideResourceHelper(@ApplicationContext context: Context): ResourceHelper {
        return ResourceHelperImpl(context)
    }

}