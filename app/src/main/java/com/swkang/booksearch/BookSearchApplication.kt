package com.swkang.booksearch

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
@HiltAndroidApp
class BookSearchApplication: MultiDexApplication() {
    val isMock = false
}