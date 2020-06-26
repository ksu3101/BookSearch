package com.swkang.booksearch

import androidx.multidex.MultiDex
import com.swkang.booksearch.base.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
class BookSearchApplication: DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.factory().create(this)
    }
}