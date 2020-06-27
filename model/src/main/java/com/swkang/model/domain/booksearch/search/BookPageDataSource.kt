package com.swkang.model.domain.booksearch.search

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.swkang.model.base.RxDisposer
import com.swkang.model.base.redux.AppStore
import com.swkang.model.domain.booksearch.BookSearchRepository
import com.swkang.model.domain.booksearch.dto.Book
import com.swkang.model.domain.common.message.ShowToastMessageAction

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
class BookPageDataSource(
    private val query: String,
    private val appStore: AppStore,
    private val repo: BookSearchRepository,
    private val disposer: RxDisposer
) : PageKeyedDataSource<BookSearchParams, Book>() {

    override fun loadInitial(
        params: LoadInitialParams<BookSearchParams>,
        callback: LoadInitialCallback<BookSearchParams, Book>
    ) {
        if (query.isEmpty()) return
        disposer.addDisposer(
            repo.requestBookSearch(query, 1)
                .subscribe(
                    {
                        callback.onResult(it.documents, null, BookSearchParams(query, it.meta.isEnd,2))
                    },
                    {
                        appStore.dispatch(ShowToastMessageAction(messageStr = it.message))
                    }
                )
        )
    }

    override fun loadAfter(
        params: LoadParams<BookSearchParams>,
        callback: LoadCallback<BookSearchParams, Book>
    ) {
        Log.d(
            "BookPageDataSource",
            "loadNextPage page = [${params.key.page}], query = `${params.key.query}`"
        )
        if (params.key.isEnd) return
        disposer.addDisposer(
            repo.requestBookSearch(params.key.query, params.key.page)
                .subscribe(
                    {
                        callback.onResult(
                            it.documents,
                            BookSearchParams(params.key.query, it.meta.isEnd, params.key.page + 1)
                        )
                    },
                    {
                        appStore.dispatch(ShowToastMessageAction(messageStr = it.message))
                    }
                )
        )
    }

    override fun loadBefore(
        params: LoadParams<BookSearchParams>,
        callback: LoadCallback<BookSearchParams, Book>
    ) {
    }
}

data class BookSearchParams(
    val query: String,
    val isEnd: Boolean,
    val page: Int
)