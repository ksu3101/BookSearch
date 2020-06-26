package com.swkang.booksearch.base.network

import com.swkang.common.ANDROID_APPKEY
import com.swkang.common.ANDROID_AUTH_HEADER
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
class AddKakaoAkHeaderIntercepter: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()
        val request = oldRequest.newBuilder()
            .addHeader(ANDROID_AUTH_HEADER, ANDROID_APPKEY)
            .method(oldRequest.method(), oldRequest.body())
            .build()

        return chain.proceed(request)
    }
}