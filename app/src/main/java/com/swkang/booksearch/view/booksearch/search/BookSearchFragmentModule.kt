package com.swkang.booksearch.view.booksearch.search

import androidx.lifecycle.ViewModel
import com.swkang.booksearch.base.di.factory.ViewModelKey
import com.swkang.model.domain.booksearch.search.BookSearchViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.multibindings.IntoMap

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
@Module
@InstallIn(FragmentComponent::class)
abstract class BookSearchFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(BookSearchViewModel::class)
    abstract fun bindBookSearchViewModel(viewModel: BookSearchViewModel): ViewModel

}
