package com.swkang.booksearch.view.booksearch.search

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.swkang.booksearch.R
import com.swkang.booksearch.base.BaseFragment
import com.swkang.model.base.BaseViewModel
import com.swkang.model.domain.booksearch.BookSearchState
import com.swkang.model.domain.booksearch.search.BookSearchViewModel
import javax.inject.Inject

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
class BookSearchFragment: BaseFragment<BookSearchState>() {
    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory
    private val vm: BookSearchViewModel by viewModels { vmFactory }

    override fun getLayoutId(): Int = R.layout.booksearch_fragment

    override fun createViewModel(): BaseViewModel<BookSearchState> = vm

}