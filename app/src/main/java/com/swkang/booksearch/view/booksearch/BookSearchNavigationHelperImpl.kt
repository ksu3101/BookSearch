package com.swkang.booksearch.view.booksearch

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.swkang.booksearch.R
import com.swkang.booksearch.view.booksearch.search.BookSearchFragmentDirections
import com.swkang.model.domain.booksearch.BookSearchNavigationHelper
import com.swkang.model.domain.booksearch.dto.Book

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
class BookSearchNavigationHelperImpl constructor(
    private val activity: AppCompatActivity
) : BookSearchNavigationHelper {

    override fun openBookDetailPage(book: Book) {
        val direction = BookSearchFragmentDirections
            .actionBookSearchFragmentToBookDetailFragment(book)
        activity.findNavController(R.id.fragmentContainer).navigate(direction)
    }

}