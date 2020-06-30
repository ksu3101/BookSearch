package com.swkang.booksearch.repositories.booksearch

import com.swkang.model.domain.booksearch.BookSearchRepository
import com.swkang.model.domain.booksearch.dto.BookResult
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
class BookSearchRepositoryImpl(
    private val api: BookSearchApi
) : BookSearchRepository {

    override fun requestBookSearch(
        query: String,
        page: Int
    ): Single<BookResult> {
        return api.requestBookSearch(query, page, 50)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}