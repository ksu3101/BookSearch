package com.swkang.model.domain.booksearch.search

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.swkang.model.base.RxDisposer
import com.swkang.model.base.redux.AppStore
import com.swkang.model.domain.booksearch.BookSearchRepository
import com.swkang.model.domain.booksearch.dto.Book


/**
 * @author kangsungwoo
 * @since 6/26/2020
 */
class BookPageDataSourceFactory(
    private val query: String,
    private val appStore: AppStore,
    private val repo: BookSearchRepository,
    private val disposer: RxDisposer
): DataSource.Factory<BookSearchParams, Book>() {
    private val bookDataSource = MutableLiveData<BookPageDataSource>()

    override fun create(): DataSource<BookSearchParams, Book> {
        val dataSource = BookPageDataSource(query, appStore, repo, disposer)
        bookDataSource.postValue(dataSource)
        return dataSource
    }

}