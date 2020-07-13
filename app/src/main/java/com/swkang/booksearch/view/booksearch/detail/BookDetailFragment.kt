package com.swkang.booksearch.view.booksearch.detail

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.swkang.booksearch.R
import com.swkang.booksearch.base.BaseFragment
import com.swkang.booksearch.view.booksearch.BookSearchActivity
import com.swkang.model.base.BaseViewModel
import com.swkang.model.domain.booksearch.BookSearchState
import com.swkang.model.domain.booksearch.detail.BookDetailViewModel
import javax.inject.Inject

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
class BookDetailFragment: BaseFragment() {
    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory
    private val vm: BookDetailViewModel by viewModels { vmFactory }
    private val bookDetailArgs by navArgs<BookDetailFragmentArgs>()

    override fun getLayoutId(): Int = R.layout.bookdetail_fragment

    override fun createViewModel(): BaseViewModel<BookSearchState> = vm

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BookSearchActivity) {
            context.expandToolbar()
            context.setUpToolbarTitle(getString(R.string.bookdetail))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val view = super.onCreateView(inflater, container, savedInstanceState)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.setBook(bookDetailArgs.book)
    }

    override fun onResume() {
        super.onResume()
        val requireActiity = requireActivity()
        if (requireActiity is BookSearchActivity) {
//            requireActiity.setUpToolbarTitle(getString(R.string.bookdetail))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
    }

}