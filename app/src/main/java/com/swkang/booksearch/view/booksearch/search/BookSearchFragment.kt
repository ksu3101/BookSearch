package com.swkang.booksearch.view.booksearch.search

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.swkang.booksearch.R
import com.swkang.booksearch.base.BaseFragment
import com.swkang.booksearch.view.booksearch.BookSearchActivity
import com.swkang.common.BOOKS_RV_STATE_KEY
import com.swkang.model.base.BaseViewModel
import com.swkang.model.domain.booksearch.search.BookSearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
@AndroidEntryPoint
class BookSearchFragment : BaseFragment() {
    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory
    private val vm: BookSearchViewModel by viewModels { vmFactory }
    private lateinit var bookRv: RecyclerView

    override fun getLayoutId(): Int = R.layout.booksearch_fragment

    override fun createViewModel(): BaseViewModel<*> = vm

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = super.onCreateView(inflater, container, savedInstanceState)
        rootView?.let {
            bookRv = it.findViewById(R.id.books_rv)
        }
        return rootView
    }

    override fun onResume() {
        super.onResume()
        val requireActivity = requireActivity()
        if (requireActivity is BookSearchActivity) {
            requireActivity.setUpToolbarTitle(
                if (requireActivity.hasQuery()) {
                    getString(R.string.book_search_tit, requireActivity.getQuery())
                } else {
                    getString(R.string.book_search)
                }

            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (::bookRv.isInitialized) {
            val bookRvState = bookRv.layoutManager?.onSaveInstanceState()
            bookRvState?.let {
                outState.putParcelable(BOOKS_RV_STATE_KEY, it)
            }
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (::bookRv.isInitialized) {
            savedInstanceState?.let { state ->
                val rvSavedState = state.getParcelable(BOOKS_RV_STATE_KEY) as Parcelable
                bookRv.layoutManager?.onRestoreInstanceState(rvSavedState)
            }
        }
    }

}