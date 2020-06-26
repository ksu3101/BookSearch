package com.swkang.booksearch.repositories.booksearch

import com.swkang.model.domain.booksearch.dto.BookResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
interface BookSearchApi {

    @GET("search/book")
    fun requestBookSearch(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Single<BookResult>

}