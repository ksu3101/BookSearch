package com.swkang.model.domain.booksearch

import com.swkang.model.base.redux.Action
import com.swkang.model.base.redux.Reducer

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
class BookSearchReducer: Reducer<BookSearchState> {

    override fun reduce(oldState: BookSearchState, resultAction: Action): BookSearchState {
        return when(resultAction) {
            is RequestBookSearchAction -> RequestBookSearchByQueryState(resultAction.query)

            else -> oldState

        }
    }

}