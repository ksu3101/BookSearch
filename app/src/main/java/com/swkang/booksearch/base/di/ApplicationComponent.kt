package com.swkang.booksearch.base.di

import android.content.Context
import com.swkang.booksearch.BookSearchApplication
import com.swkang.booksearch.view.booksearch.BookSearchActivityModule
import com.swkang.model.base.helper.MessageHelper
import com.swkang.model.base.redux.AppStore
import com.swkang.model.domain.booksearch.BookSearchRepository
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
@Singleton
@Component(
    modules = [
        // common modules
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        NetworkModule::class,
        ReducerModule::class,

        // subcomponent modules
        BookSearchActivityModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<BookSearchApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }

    fun appStore(): AppStore

    fun messageHelper(): MessageHelper

    fun bookSearchRepository(): BookSearchRepository

}
