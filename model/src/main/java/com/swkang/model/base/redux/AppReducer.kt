package com.swkang.model.base.redux

import com.swkang.model.domain.booksearch.BookSearchReducer
import com.swkang.model.domain.common.message.MessageReducer

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
class AppReducer(
    private val messageReducer: MessageReducer,
    private val bookSearchReducer: BookSearchReducer
): Reducer<AppState> {

    override fun reduce(oldState: AppState, resultAction: Action): AppState {
        return AppState(
            messageReducer.reduce(oldState.messageState, resultAction),
            bookSearchReducer.reduce(oldState.bookSearchState, resultAction)
        )
    }

}