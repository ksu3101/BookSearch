package com.swkang.booksearch.view.booksearch

import android.app.Activity
import com.swkang.booksearch.base.di.scope.ActivityScope
import com.swkang.booksearch.view.booksearch.detail.BookDetailFragmentModule
import com.swkang.booksearch.view.booksearch.search.BookSearchFragmentModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
@Module
abstract class BookSearchActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            BookSearchFragmentModule::class,
            BookDetailFragmentModule::class
        ]
    )
    abstract fun bookSearchActivity(): BookSearchActivity

    @Binds
    @ActivityScope
    abstract fun bindBookSearchActivity(activity: BookSearchActivity): Activity

}
