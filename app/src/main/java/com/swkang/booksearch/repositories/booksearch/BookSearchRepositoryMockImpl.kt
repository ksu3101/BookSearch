package com.swkang.booksearch.repositories.booksearch

import com.swkang.model.domain.booksearch.BookSearchRepository
import com.swkang.model.domain.booksearch.dto.Book
import com.swkang.model.domain.booksearch.dto.BookResult
import com.swkang.model.domain.booksearch.dto.Meta
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class BookSearchRepositoryMockImpl: BookSearchRepository {

    companion object {
        val DUMMY_BOOKRESULT = BookResult(
            Meta(true, 1, 2),
            listOf(
                Book(
                    "책 이름 1",
                    "책에 대한 상세한 내용 어쩌구 저쩌구..",
                    "http://",
                    "isbn",
                    "2009",
                    listOf("글쓴이"),
                    "출판사",
                    listOf("번역자"),
                    12000,
                    11500,
                    "http://",
                    "status"
                ),
                Book(
                    "책 이름 2",
                    "22 22 책에 대한 상세한 내용 어쩌구 저쩌구..",
                    "http://",
                    "isbn",
                    "2010",
                    listOf("글쓴이 2"),
                    "출판사 2",
                    listOf("번역자 2"),
                    25000,
                    24000,
                    "http://",
                    "status"
                )
            )
        )
    }

    override fun requestBookSearch(query: String, page: Int): Single<BookResult> {
        return Single.just(DUMMY_BOOKRESULT)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}