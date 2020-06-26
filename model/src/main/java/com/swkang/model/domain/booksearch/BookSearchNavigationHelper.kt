package com.swkang.model.domain.booksearch

import com.swkang.model.base.helper.NavigationHelper
import com.swkang.model.domain.booksearch.dto.Book

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
interface BookSearchNavigationHelper: NavigationHelper {

    fun openBookDetailPage(book: Book)

}