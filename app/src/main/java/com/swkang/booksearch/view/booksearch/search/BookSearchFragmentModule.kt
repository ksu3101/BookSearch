package com.swkang.booksearch.view.booksearch.search

import androidx.lifecycle.ViewModel
import com.swkang.booksearch.base.di.factory.ViewModelBuilder
import com.swkang.booksearch.base.di.factory.ViewModelKey
import com.swkang.booksearch.view.booksearch.BookSearchNavigationHelperImpl
import com.swkang.model.domain.booksearch.BookSearchNavigationHelper
import com.swkang.model.domain.booksearch.search.BookSearchViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
@Module
abstract class BookSearchFragmentModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    abstract fun bookSearchFragment(): BookSearchFragment

    @Binds
    @IntoMap
    @ViewModelKey(BookSearchViewModel::class)
    abstract fun bindBookSearchViewModel(viewModel: BookSearchViewModel): ViewModel

    @Binds
    abstract fun bindNavigationHelper(
        navigationHelper: BookSearchNavigationHelperImpl
    ): BookSearchNavigationHelper

}
