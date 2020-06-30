package com.swkang.model.domain.booksearch

import com.swkang.model.domain.booksearch.dto.BookResult
import io.reactivex.rxjava3.core.Single

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
interface BookSearchRepository {

    fun requestBookSearch(
        query: String,
        page: Int
    ): Single<BookResult>

}