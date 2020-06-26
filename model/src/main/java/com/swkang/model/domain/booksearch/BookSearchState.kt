package com.swkang.model.domain.booksearch

import com.swkang.model.base.redux.State

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */

sealed class BookSearchState : State

object InitializedState: BookSearchState()

data class RequestBookSearchByQueryState(
    val query: String
): BookSearchState()
