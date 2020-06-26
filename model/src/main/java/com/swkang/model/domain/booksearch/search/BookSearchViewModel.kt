package com.swkang.model.domain.booksearch.search

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.swkang.common.BOOKSEARCH_LOADPAGE_PREFETCH_DISTANCE
import com.swkang.common.BOOKSEARCH_LOADPAGE_SIZE
import com.swkang.model.base.BaseViewModel
import com.swkang.model.base.redux.AppStore
import com.swkang.model.domain.booksearch.*
import com.swkang.model.domain.booksearch.dto.Book
import javax.inject.Inject

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
class BookSearchViewModel @Inject constructor(
    val appStore: AppStore,
    val repo: BookSearchRepository,
    val navigationHelper: BookSearchNavigationHelper
) : BaseViewModel<BookSearchState>() {
    var books: LiveData<PagedList<Book>>
    private val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(BOOKSEARCH_LOADPAGE_SIZE)
        .setPageSize(BOOKSEARCH_LOADPAGE_SIZE)
        .setPrefetchDistance(BOOKSEARCH_LOADPAGE_PREFETCH_DISTANCE)
        .build()

    val onBookItemClicked: (Book) -> Unit = {
        navigationHelper.openBookDetailPage(it)
    }

    init {
        books = createLivePagedListDatas("")
    }

    override fun render(state: BookSearchState): Boolean {
        return when (state) {
            is InitializedState -> {
                true
            }

            is RequestBookSearchByQueryState -> {
                books = createLivePagedListDatas(state.query)
                true
            }

            else -> false
        }
    }

    private fun createLivePagedListDatas(query: String): LiveData<PagedList<Book>> {
        return LivePagedListBuilder(
            BookPageDataSourceFactory(query, appStore, repo, this),
            config
        ).build()
    }

}