package com.swkang.booksearch.view.booksearch.detail

import androidx.lifecycle.ViewModel
import com.swkang.booksearch.base.di.factory.ViewModelBuilder
import com.swkang.booksearch.base.di.factory.ViewModelKey
import com.swkang.model.domain.booksearch.detail.BookDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
@Module
abstract class BookDetailFragmentModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    abstract fun bookDetailFragment(): BookDetailFragment

    @Binds
    @IntoMap
    @ViewModelKey(BookDetailViewModel::class)
    abstract fun bindBookDetailViewModel(viewModel: BookDetailViewModel): ViewModel

}
