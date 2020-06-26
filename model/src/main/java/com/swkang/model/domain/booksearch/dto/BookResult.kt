package com.swkang.model.domain.booksearch.dto

import com.squareup.moshi.Json

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
data class BookResult(
    val meta: Meta,
    val documents: List<Book>
)

data class Meta(
    @Json(name = "is_end") val isEnd: Boolean,
    @Json(name = "pageable_count") val pageableCount: Int,
    @Json(name = "total_count") val totalCount: Int
)