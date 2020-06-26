package com.swkang.booksearch.view.booksearch

import androidx.navigation.findNavController
import com.swkang.booksearch.R
import com.swkang.booksearch.view.booksearch.search.BookSearchFragmentDirections
import com.swkang.model.domain.booksearch.BookSearchNavigationHelper
import com.swkang.model.domain.booksearch.dto.Book
import javax.inject.Inject

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
class BookSearchNavigationHelperImpl @Inject constructor(
    val activity: BookSearchActivity
) : BookSearchNavigationHelper {

    override fun openBookDetailPage(book: Book) {
        val direction = BookSearchFragmentDirections
            .actionBookSearchFragmentToBookDetailFragment(book)
        activity.findNavController(R.id.fragmentContainer).navigate(direction)
    }

}