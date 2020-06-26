package com.swkang.booksearch.view.booksearch.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.swkang.booksearch.R
import com.swkang.booksearch.base.BaseFragment
import com.swkang.model.base.BaseViewModel
import com.swkang.model.domain.booksearch.BookSearchState
import com.swkang.model.domain.booksearch.detail.BookDetailViewModel
import javax.inject.Inject

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
class BookDetailFragment: BaseFragment<BookSearchState>() {
    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory
    private val vm: BookDetailViewModel by viewModels { vmFactory }
    private val bookDetailArgs by navArgs<BookDetailFragmentArgs>()

    override fun getLayoutId(): Int = R.layout.bookdetail_fragment

    override fun createViewModel(): BaseViewModel<BookSearchState> = vm

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.setBook(bookDetailArgs.book)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_bookdetail, menu)
    }

}