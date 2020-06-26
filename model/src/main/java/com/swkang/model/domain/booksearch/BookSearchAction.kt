package com.swkang.model.domain.booksearch

import com.swkang.model.base.redux.Action
import com.swkang.model.domain.booksearch.dto.Book

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */

sealed class BookSearchAction : Action

object InitializedAction: BookSearchAction()

/**
 * `sort, size, target`등 패러미터는 제외
 */
data class RequestBookSearchAction(
    val query: String,
    val page: Int = 1
): BookSearchAction()

data class BookPageResultAction(
    val query: String,
    val page: Int,
    val isEnd: Boolean,
    val books: List<Book>
): BookSearchAction()
