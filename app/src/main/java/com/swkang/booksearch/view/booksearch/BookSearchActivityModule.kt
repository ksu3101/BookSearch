package com.swkang.booksearch.view.booksearch

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.swkang.model.domain.booksearch.BookSearchNavigationHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

/**
 * @author kangsungwoo
 * @since 7/12/2020
 */
@Module
@InstallIn(ActivityComponent::class)
object BookSearchActivityModule {

    @ActivityScoped
    @Provides
    fun provideBookSearchNavigationHelper(
        @ActivityContext context: Context
    ): BookSearchNavigationHelper {
        return BookSearchNavigationHelperImpl(context as BookSearchActivity)
    }

}